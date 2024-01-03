package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.component.getRandomCard
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onDrawNewCard: () -> Unit,
    onUpdateCurrentUser: (user: String) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }

        else -> {

        }
    }

}


@LandscapePreviews
@Composable
fun LandscapeClassicCardScreenPreview() {
    LandscapeClassicCardScreen(
        uiState = ClassicCardUiState.Ready(
            numbers = getRandomCard(),
            currentUser = "Ronaldo"
        ),
        onDrawNewCard = { },
        onUpdateCurrentUser = { }
    )
}