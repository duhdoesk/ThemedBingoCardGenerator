package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.HostSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.PlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun SessionScreen(
    sessionViewModel: SessionViewModel = hiltViewModel()
) {

    val state = sessionViewModel.sessionUiState
        .collectAsStateWithLifecycle()
        .value

    when (state) {

        is SessionUiState.Success -> {
            if (state.isHost) {
                HostSessionScreen(state = state) {

                }
            }

            else {
                PlayerSessionScreen(state = state) {

                }
            }
        }

        is SessionUiState.Error ->
            ErrorScreen(
                errorMessage = state.message,
                onTryAgain = {  })

        else ->
            LoadingScreen()
    }
}