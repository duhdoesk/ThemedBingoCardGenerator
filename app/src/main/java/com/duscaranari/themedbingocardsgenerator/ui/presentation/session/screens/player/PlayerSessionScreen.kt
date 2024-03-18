package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.landscape.LandscapePlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.portrait.PortraitPlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun PlayerSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitPlayerSessionScreen(state = state) { event(it) }

        else ->
            LandscapePlayerSessionScreen(state = state) { event(it) }
    }
}