package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.portrait.PortraitCardScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.ThemesScreen
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.event.ThemedCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.landscape.NewLandscapeCardScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun CardScreen(
    navController: NavHostController,
    cardViewModel: CardViewModel = hiltViewModel()
) {

    when (val state = cardViewModel.uiState.collectAsStateWithLifecycle().value) {
        is CardUiState.Loading -> LoadingScreen()

        is CardUiState.PendingTheme -> {
            ThemesScreen(
                onThemePick = { cardViewModel.onSelectTheme(it) }
            )
        }

        is CardUiState.Error -> ErrorScreen(
            errorMessage = state.errorMessage,
            onTryAgain = { cardViewModel.onResetState() }
        )

        is CardUiState.Success -> {
            when (rememberDeviceOrientation()) {

                is DeviceOrientation.Landscape ->
                    NewLandscapeCardScreen(
                        state = state,
                        event = { event ->
                            when (event) {
                                is ThemedCardUiEvent.OnChangeCardSize ->
                                    cardViewModel.onChangeCardSize()

                                is ThemedCardUiEvent.OnDrawNewCard ->
                                    cardViewModel.onDrawNewCard()

                                is ThemedCardUiEvent.OnUpdateCurrentUser ->
                                    cardViewModel.onUpdateCurrentUser(event.user)

                                is ThemedCardUiEvent.OnNavigateToCharactersScreen ->
                                    navController.navigate(
                                        "${AppScreens.Character.name}/${state.currentTheme.id}"
                                    )
                            }
                        }
                    )

                else ->
                    PortraitCardScreen(
                        state = state,
                        event = { event ->
                            when (event) {
                                is ThemedCardUiEvent.OnChangeCardSize ->
                                    cardViewModel.onChangeCardSize()

                                is ThemedCardUiEvent.OnDrawNewCard ->
                                    cardViewModel.onDrawNewCard()

                                is ThemedCardUiEvent.OnUpdateCurrentUser ->
                                    cardViewModel.onUpdateCurrentUser(event.user)

                                is ThemedCardUiEvent.OnNavigateToCharactersScreen ->
                                    navController.navigate(
                                        "${AppScreens.Character.name}/${state.currentTheme.id}"
                                    )
                            }
                        }
                    )
            }
        }
    }
}