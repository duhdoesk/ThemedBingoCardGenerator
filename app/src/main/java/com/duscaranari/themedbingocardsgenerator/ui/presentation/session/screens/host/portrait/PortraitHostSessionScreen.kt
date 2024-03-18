package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.mockSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitHostSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {
        is WindowInfo.WindowType.Compact ->
            CompactPortraitSessionScreen(state = state) {
                event(it)
            }

        else ->
            ExpandedPortraitSessionScreen(state = state) {
                event(it)
            }
    }
}

@PortraitPreviews
@Composable
fun Preview() {
    PortraitHostSessionScreen(state = mockSessionUiState()) {  }
}