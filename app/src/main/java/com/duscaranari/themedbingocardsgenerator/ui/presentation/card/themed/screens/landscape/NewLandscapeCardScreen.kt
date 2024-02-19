package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.event.ThemedCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.getRawListOfCharacters
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun NewLandscapeCardScreen(
    state: CardUiState.Success,
    event: (event: ThemedCardUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {

        is WindowInfo.WindowType.Compact ->
            RotateScreen()

        is WindowInfo.WindowType.Medium ->
            MediumLandscapeCardScreen(
                state = state,
                event = event
            )

        is WindowInfo.WindowType.Expanded ->
            ExpandedLandscapeCardScreen(
                state = state,
                event = event
            )
    }
}

@LandscapePreviews
@Composable
fun LandscapePreview() {

    val characters = getRawListOfCharacters()

    NewLandscapeCardScreen(
        state = CardUiState.Success(
            currentTheme = Theme(
                themeId = "1",
                themeName = "Bears",
                themePicture = ""
            ),
            currentUser = "Dwight Schrüte",
            drawnCharacters = characters,
            themeCharacters = characters,
            cardSize = CardSize.LARGE
        ),
        event = { }
    )
}