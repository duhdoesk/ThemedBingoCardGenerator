package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.component.ClassicCardGrid
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.component.getRandomCard
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.CardScreenName
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.NewCardButton
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onDrawNewCard: () -> Unit,
    onUpdateCurrentUser: (user: String) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .sizeIn(maxWidth = 600.dp, maxHeight = 1000.dp)
        ) {
            ClassicCardGrid(numbers = uiState.numbers)

            CardScreenName(
                onChange = { onUpdateCurrentUser(it) },
                currentUser = uiState.currentUser,
            )

            NewCardButton(
                onClick = { onDrawNewCard() }
            )
        }
    }
}


@PortraitPreviews
@Composable
fun PortraitClassicCardScreenPreview() {
    PortraitClassicCardScreen(
        uiState = ClassicCardUiState.Ready(
            numbers = getRandomCard(),
            currentUser = "Ronaldo"
        ),
        onDrawNewCard = { },
        onUpdateCurrentUser = { }
    )
}