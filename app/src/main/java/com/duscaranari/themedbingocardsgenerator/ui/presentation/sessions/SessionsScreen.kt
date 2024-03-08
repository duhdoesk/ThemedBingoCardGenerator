package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.JoinResult
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component.JoinSessionDialog
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component.SessionsScreenLazyGrid
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData

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
    var currentSession: MutableState<Session?> = remember {
        mutableStateOf(null)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Text(
            text = stringResource(R.string.tap_in_a_session_to_join_it),
            modifier = Modifier.fillMaxWidth()
        )

        SessionsScreenLazyGrid(
            sessions = sessions,
            themes = themes,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            onJoinSession = { session ->

                when (googleUser) {
                    null -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.login_needed),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    else -> {
                        currentSession.value = session
                        showJoinSessionDialog = true
                    }
                }
            }
        )

        Button(
            onClick = { navController.navigate(AppScreens.CreateSession.name) }
        ) {
            Text(text = stringResource(id = R.string.create_session_button))
        }

        if (showJoinSessionDialog) {

            if (currentSession.value != null) {
                JoinSessionDialog(
                    onDismiss = { showJoinSessionDialog = false },
                    session = currentSession.value!!,
                    onJoinSession = { password ->

                        when (
                            val joinResult = sessionsViewModel.onJoinSession(
                                session = currentSession.value,
                                userData = googleUser,
                                password = password
                            )
                        ) {
                            is JoinResult.Success ->
                                navController.navigate(AppScreens.Home.name)

                            is JoinResult.Error -> {
                                showJoinSessionDialog = false

                                Toast.makeText(
                                    context,
                                    joinResult.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

