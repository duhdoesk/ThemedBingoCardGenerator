package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.CardState

@Composable
fun PortraitCardScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardState.Ready,
    onNavToCharactersScreen: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        CardScreenHeader(theme = state.currentTheme)

        CardScreenLazyVerticalGrid(
            characters = state.drawnCharacters,
            columns = 3,
            modifier = Modifier
                .widthIn(max = 600.dp)
                .clickable { onNavToCharactersScreen() }
        )

        CardScreenName(
            onChange = { onUpdateCurrentUser(it) },
            currentUser = state.currentUser,
        )

        NewCardButton(
            onClick = { onDrawNewCard() }
        )
    }
}