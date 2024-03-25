package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.HostSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.PlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun HostOrPlayerSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    if (state.isHost) {
        HostSessionScreen(
            state = state,
            event = { event(it) }
        )
    } else {
        PlayerSessionScreen(
            state = state,
            event = { event(it) }
        )
    }
}