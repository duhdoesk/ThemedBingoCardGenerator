package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.landscape

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.mockSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews

@Composable
fun LandscapeHostSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 24.dp)
            .fillMaxSize()
    ) {
        when (state.sessionState) {
            SessionState.NOT_STARTED ->
                LandscapeHostSessionScreenNotStarted(
                    state = state,
                    event = { event(it) }
                )

            else ->
                LandscapeHostSessionScreenDrawing(
                    state = state,
                    event = { event(it) }
                )
        }
    }
}

@LandscapePreviews
@Composable
fun Preview() {
    LandscapeHostSessionScreen(state = mockSessionUiState()) { }
}