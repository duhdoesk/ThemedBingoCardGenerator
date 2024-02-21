package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.portrait

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.CardScreenUserButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.NewCardButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.event.ThemedCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component.CardScreenGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component.CardScreenHeader
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component.ThemedCardSizeSwitcher
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState

@Composable
fun ExpandedPortraitCardScreen(
    state: CardUiState.Success,
    event: (event: ThemedCardUiEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CardScreenHeader(
            themeName = state.currentTheme.name,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 4.dp)
        )

        CardScreenGrid(
            characters = state.drawnCharacters,
            cardSize = state.cardSize,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .clickable { event(ThemedCardUiEvent.OnNavigateToCharactersScreen) }
        )

        Row(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(0.7f)
        ) {

            CardScreenUserButton(
                onChange = { event(ThemedCardUiEvent.OnUpdateCurrentUser(it)) },
                currentUser = state.currentUser,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(12.dp))

            NewCardButton { event(ThemedCardUiEvent.OnDrawNewCard) }
        }

        Spacer(modifier = Modifier.height(32.dp))

        ThemedCardSizeSwitcher(
            cardSize = state.cardSize,
            onClick = { event(ThemedCardUiEvent.OnChangeCardSize) },
            parentShape = RoundedCornerShape(32.dp),
            toggleShape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(40.dp)
        )
    }
}