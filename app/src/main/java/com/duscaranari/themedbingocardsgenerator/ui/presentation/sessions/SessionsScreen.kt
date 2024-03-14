package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.JoinResult
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component.JoinSessionDialog
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.landscape.LandscapeSessionsScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.portrait.PortraitSessionsScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun SessionsScreen(
    sessionsViewModel: SessionsViewModel = hiltViewModel(),
    navController: NavController,
    googleUser: UserData?
) {

    val sessions = sessionsViewModel.sessions
        .collectAsStateWithLifecycle()
        .value

    val themes = sessionsViewModel.themes
        .collectAsStateWithLifecycle()
        .value

    val context = LocalContext.current

    var showJoinSessionDialog by remember { mutableStateOf(false) }
    val currentSession: MutableState<SessionDTO?> = remember {
        mutableStateOf(null)
    }

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Landscape -> {
            LandscapeSessionsScreen(
                sessions = sessions,
                themes = themes,
                onPickASession = {
                    currentSession.value = it
                    showJoinSessionDialog = true
                },
                onNavigateToCreateASession = {
                    navController.navigate(AppScreens.CreateSession.name)
                }
            )
        }

        is DeviceOrientation.Portrait ->
            PortraitSessionsScreen(
                sessions = sessions,
                themes = themes,
                onPickASession = {
                    currentSession.value = it
                    showJoinSessionDialog = true
                },
                onNavigateToCreateASession = {
                    navController.navigate(AppScreens.CreateSession.name)
                }
            )
    }

    if (showJoinSessionDialog) {
        currentSession.value?.let { session ->
            JoinSessionDialog(
                onDismiss = { showJoinSessionDialog = false },
                session = session,
                onJoinSession = { password ->

                    when (
                        val joinResult = sessionsViewModel.onJoinSession(
                            session = session,
                            userData = googleUser,
                            password = password
                        )
                    ) {
                        is JoinResult.Success ->
                            navController.navigate(
                                "${AppScreens.Session.name}/${session.id}"
                            )

                        is JoinResult.Error -> {
                            showJoinSessionDialog = false

                            Toast.makeText(
                                context,
                                context.getString(joinResult.message),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}