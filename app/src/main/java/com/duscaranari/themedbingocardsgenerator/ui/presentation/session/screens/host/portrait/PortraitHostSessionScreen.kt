package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.portrait

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
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitHostSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .fillMaxSize()
    ) {
            when (state.sessionState) {
                SessionState.NOT_STARTED ->
                    PortraitHostSessionScreenNotStarted(
                        state = state,
                        event = { event(it) }
                    )

                SessionState.DRAWING ->
                    PortraitHostSessionScreenDrawing(
                        state = state,
                        event = { event(it) }
                    )

                SessionState.FINISHED ->
                    PortraitHostSessionScreenFinished(
                        state = state,
                        event = { event(it) }
                    )
            }
        }
    }

@PortraitPreviews
@Composable
fun Preview() {
    PortraitHostSessionScreen(state = mockSessionUiState()) { }
}