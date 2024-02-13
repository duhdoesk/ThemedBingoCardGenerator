package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.portrait

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
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
fun CompactPortraitCardScreen(
    state: CardUiState.Success,
    event: (event: ThemedCardUiEvent) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            CardScreenHeader(
                themeName = state.currentTheme.themeName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )

            CardScreenGrid(
                characters = state.drawnCharacters,
                cardSize = state.cardSize,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { event(ThemedCardUiEvent.OnNavigateToCharactersScreen) }
            )

            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
            ) {

                CardScreenUserButton(
                    onChange = { event(ThemedCardUiEvent.OnUpdateCurrentUser(it)) },
                    currentUser = state.currentUser,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                NewCardButton { event(ThemedCardUiEvent.OnDrawNewCard) }
            }
        }

        ThemedCardSizeSwitcher(
            cardSize = state.cardSize,
            onClick = { event(ThemedCardUiEvent.OnChangeCardSize) },
            parentShape = RoundedCornerShape(32.dp),
            toggleShape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
    }

}