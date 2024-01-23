package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.LandscapeCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.PortraitCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.ThemesScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.getRawListOfCharacters
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun CardScreen(
    navController: NavHostController,
    cardViewModel: CardViewModel = hiltViewModel()
) {

    when (val state = cardViewModel.cardUiState.collectAsStateWithLifecycle().value) {
        is CardUiState.Loading -> LoadingScreen()

        is CardUiState.PendingTheme -> {
            ThemesScreen(
                themes = state.themes,
                onClick = { cardViewModel.selectTheme(it) }
            )
        }

        is CardUiState.Error -> ErrorScreen(
            errorMessage = state.errorMessage,
            onTryAgain = { cardViewModel.resetState() }
        )
        
        is CardUiState.Success -> {
            when (rememberDeviceOrientation()) {

                is DeviceOrientation.Landscape ->
                    LandscapeCardScreen(
                        state = state,
                        onUpdateCurrentUser = { cardViewModel.updateCurrentUser(it) },
                        onDrawNewCard = { cardViewModel.drawNewCard() },
                        onNavToCharactersScreen = {
                            navController.navigate(
                                "${AppScreens.Character.name}/${state.currentTheme.themeId}"
                            )
                        },
                        onChangeCardSize = { cardViewModel.changeCardSize(it) }
                    )

                else ->
                    PortraitCardScreen(
                        state = state,
                        onUpdateCurrentUser = { cardViewModel.updateCurrentUser(it) },
                        onDrawNewCard = { cardViewModel.drawNewCard() },
                        onNavToCharactersScreen = {
                            navController.navigate(
                                "${AppScreens.Character.name}/${state.currentTheme.themeId}"
                            )
                        },
                        onChangeCardSize = { cardViewModel.changeCardSize(it) }
                    )
            }
        }
    }
}


// PREVIEWS

@PortraitPreviews
@Composable
fun PortraitPreview() {

    val characters = getRawListOfCharacters()

    PortraitCardScreen(
        state = CardUiState.Success(
            currentTheme = Theme(
                themeId = "1",
                themeName = "Bears",
                themePicture = ""
            ),
            currentUser = "Dwight Schrute",
            drawnCharacters = characters,
            themeCharacters = characters,
            cardSize = CardSize.MEDIUM
        ),
        onUpdateCurrentUser = { },
        onDrawNewCard = { },
        onNavToCharactersScreen = { },
        onChangeCardSize = { }
    )
}

@LandscapePreviews
@Composable
fun LandscapePreview() {

    val characters = getRawListOfCharacters()

    LandscapeCardScreen(
        onUpdateCurrentUser = { },
        onDrawNewCard = { },
        onNavToCharactersScreen = { },
        state = CardUiState.Success(
            currentTheme = Theme(
                themeId = "1",
                themeName = "Bears",
                themePicture = ""
            ),
            currentUser = "Dwight Schrute",
            drawnCharacters = characters,
            themeCharacters = characters,
            cardSize = CardSize.LARGE
        ),
        onChangeCardSize = { }
    )
}