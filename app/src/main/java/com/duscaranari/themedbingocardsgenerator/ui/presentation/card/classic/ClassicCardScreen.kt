package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.event.ClassicCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.landscape.LandscapeClassicCardScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.portrait.PortraitClassicCardScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun ClassicCardScreen(viewModel: ClassicCardViewModel = hiltViewModel()) {

    when (val uiState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is ClassicCardUiState.Loading -> LoadingScreen()

        is ClassicCardUiState.Ready -> {

            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Portrait -> {
                    PortraitClassicCardScreen(
                        uiState = uiState,
                        onEvent = { event ->
                            when (event) {
                                is ClassicCardUiEvent.OnDrawNewCard ->
                                    viewModel.drawNewCard()

                                is ClassicCardUiEvent.OnUpdateCurrentUser ->
                                    viewModel.updateCurrentUser(event.user)
                            }
                        }
                    )
                }

                is DeviceOrientation.Landscape -> {
                    LandscapeClassicCardScreen(
                        uiState = uiState,
                        onEvent = { event ->
                            when (event) {
                                is ClassicCardUiEvent.OnDrawNewCard ->
                                    viewModel.drawNewCard()

                                is ClassicCardUiEvent.OnUpdateCurrentUser ->
                                    viewModel.updateCurrentUser(event.user)
                            }
                        }
                    )
                }
            }
        }
    }
}