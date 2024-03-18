package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.landscape.LandscapeHostSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.portrait.PortraitHostSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun HostSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitHostSessionScreen(state = state) { event(it) }

        else ->
            LandscapeHostSessionScreen(state = state) { event(it) }
    }
}