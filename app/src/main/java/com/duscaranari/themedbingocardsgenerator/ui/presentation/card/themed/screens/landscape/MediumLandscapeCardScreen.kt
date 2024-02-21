package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.landscape

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
fun MediumLandscapeCardScreen(
    state: CardUiState.Success,
    event: (event: ThemedCardUiEvent) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterHorizontally,
            space = 48.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(maxHeight / (1.6).toFloat())
            ) {

                CardScreenHeader(
                    themeName = state.currentTheme.name,
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

                CardScreenUserButton(
                    onChange = { event(ThemedCardUiEvent.OnUpdateCurrentUser(it)) },
                    currentUser = state.currentUser,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            ),
            modifier = Modifier
                .width(240.dp)
                .fillMaxHeight()
        ) {

            ThemedCardSizeSwitcher(
                cardSize = state.cardSize,
                onClick = { event(ThemedCardUiEvent.OnChangeCardSize) },
                parentShape = RoundedCornerShape(32.dp),
                toggleShape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )

            NewCardButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick =  { event(ThemedCardUiEvent.OnDrawNewCard) }
            )
        }
    }
}