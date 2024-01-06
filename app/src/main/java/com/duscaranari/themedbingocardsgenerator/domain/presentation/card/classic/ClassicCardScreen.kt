package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.LandscapeClassicCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.PortraitClassicCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun ClassicCardScreen(classicCardViewModel: ClassicCardViewModel = hiltViewModel()) {

    when (val uiState = classicCardViewModel.uiState.collectAsStateWithLifecycle().value) {
        is ClassicCardUiState.Loading -> LoadingScreen()

        is ClassicCardUiState.Ready -> {

            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Portrait -> {
                    PortraitClassicCardScreen(
                        uiState = uiState,
                        onDrawNewCard = { classicCardViewModel.drawNewCard() },
                        onUpdateCurrentUser = { classicCardViewModel.updateCurrentUser(it) }
                    )
                }

                is DeviceOrientation.Landscape -> {
                    LandscapeClassicCardScreen(
                        uiState = uiState,
                        onDrawNewCard = { classicCardViewModel.drawNewCard() },
                        onUpdateCurrentUser = { classicCardViewModel.updateCurrentUser(it) }
                    )
                }
            }
        }
    }
}