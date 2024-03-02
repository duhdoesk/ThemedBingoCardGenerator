package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event.CreateSessionEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event.createSessionScreenEventHandler
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.landscape.LandscapeCreateSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.portrait.PortraitCreateSessionScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun CreateSessionScreen(createSessionViewModel: CreateSessionViewModel = hiltViewModel()) {
    val uiState =
        createSessionViewModel.uiState.collectAsStateWithLifecycle().value

    val isFormOk =
        createSessionViewModel.isFormOk.collectAsStateWithLifecycle().value

    val themes =
        createSessionViewModel.themes.collectAsStateWithLifecycle().value.sortedBy { it.name }

    val context =
        LocalContext.current

    fun eventHandler(event: CreateSessionEvent) = createSessionScreenEventHandler(
        event = event,
        viewModel = createSessionViewModel,
        context = context)

    when (rememberDeviceOrientation()) {
        DeviceOrientation.Landscape ->
            LandscapeCreateSessionScreen(
                uiState = uiState,
                themes = themes,
                isFormOk = isFormOk
            ) { event ->
                eventHandler(event)
            }

        DeviceOrientation.Portrait ->
            PortraitCreateSessionScreen(
                uiState = uiState,
                themes = themes,
                isFormOk = isFormOk
            ) { event ->
                eventHandler(event)
            }
    }
}