package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews

@Composable
fun LandscapeClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onDrawNewCard: () -> Unit,
    onUpdateCurrentUser: (user: String) -> Unit
) {

}


@LandscapePreviews
@Composable
fun LandscapeClassicCardScreenPreview() {
    LandscapeClassicCardScreen(
        uiState = ClassicCardUiState.Ready(
            numbers = (0..75).toList().shuffled().subList(0, 24),
            currentUser = "Ronaldo"
        ),
        onDrawNewCard = { },
        onUpdateCurrentUser = { }
    )
}