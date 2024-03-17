package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.landscape.LandscapeSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.portrait.PortraitSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun SessionScreen(
    sessionViewModel: SessionViewModel = hiltViewModel()
) {

    val session = sessionViewModel.sessionUiState
        .collectAsStateWithLifecycle()
        .value

    when (session) {

        is SessionUiState.Success -> {
            if (rememberDeviceOrientation() is DeviceOrientation.Portrait) {
                PortraitSessionScreen(state = session) {

                }
            }
            else {
                LandscapeSessionScreen(state = session) {

                }
            }
        }

        is SessionUiState.Error ->
            ErrorScreen(
                errorMessage = session.message,
                onTryAgain = {  })

        else ->
            LoadingScreen()
    }
}