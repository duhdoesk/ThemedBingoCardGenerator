package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event.CreateSessionEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState

@Composable
fun LandscapeCreateSessionScreen(
    uiState: CreateSessionUiState,
    themes: List<BingoTheme>,
    isFormOk: Boolean,
    event: (event: CreateSessionEvent) -> Unit
) {

}