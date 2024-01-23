package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.state.CardUiState

@Composable
fun PortraitCardScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardUiState.Success,
    onNavToCharactersScreen: () -> Unit,
    onChangeCardSize: (boolean: Boolean) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {

            CardScreenHeader(theme = state.currentTheme)

            CardScreenGrid(
                characters = state.drawnCharacters,
                cardSize = state.cardSize,
                modifier = Modifier
                    .widthIn(max = 560.dp)
                    .clickable { onNavToCharactersScreen() }
            )

            CardScreenName(
                onChange = { onUpdateCurrentUser(it) },
                currentUser = state.currentUser,
            )

            NewCardButton(
                onClick = { onDrawNewCard() }
            )

            SizeSelectorSwitchButton(
                optionSelected = state.cardSize,
                onClick = { onChangeCardSize(it) }
            )
        }
    }
}