package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onDrawNewCard: () -> Unit,
    onUpdateCurrentUser: (user: String) -> Unit
) {

}


@PortraitPreviews
@Composable
fun PortraitClassicCardScreenPreview() {
    PortraitClassicCardScreen(
        uiState = ClassicCardUiState.Ready(
            numbers = (0..75).toList().shuffled().subList(0, 24),
            currentUser = "Ronaldo"
        ),
        onDrawNewCard = { },
        onUpdateCurrentUser = { }
    )
}