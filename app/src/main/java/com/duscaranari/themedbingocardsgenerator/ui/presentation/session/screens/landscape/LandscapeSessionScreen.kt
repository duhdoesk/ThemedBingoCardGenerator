package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.mockSessionUiState

@Composable
fun LandscapeSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {

}

@Composable
fun Preview() {
    LandscapeSessionScreen(state = mockSessionUiState()) {

    }
}