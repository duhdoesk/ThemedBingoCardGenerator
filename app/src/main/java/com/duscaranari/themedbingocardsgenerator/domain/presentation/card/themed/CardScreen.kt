package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.LandscapeCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.PortraitCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.LoadingScreen
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

    when (val state = cardViewModel.cardState.collectAsState().value) {
        is CardState.Loading ->
            LoadingScreen()

        is CardState.Ready -> {

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
        state = CardState.Ready(
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
        state = CardState.Ready(
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