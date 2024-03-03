package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event.CreateSessionEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.portrait.PortraitCreateSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.mockSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeCreateSessionScreen(
    uiState: CreateSessionUiState,
    themes: List<BingoTheme>,
    isFormOk: Boolean,
    event: (event: CreateSessionEvent) -> Unit
) {
    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact ->
            RotateScreen()

        else ->
            PortraitCreateSessionScreen(
                uiState = uiState,
                themes = themes,
                isFormOk = isFormOk,
                event = { event(it) }
            )
    }
}

@LandscapePreviews
@Composable
fun Preview() {
    LandscapeCreateSessionScreen(
        uiState = mockSessionUiState(),
        themes = emptyList(),
        isFormOk = true,
        event = { })
}