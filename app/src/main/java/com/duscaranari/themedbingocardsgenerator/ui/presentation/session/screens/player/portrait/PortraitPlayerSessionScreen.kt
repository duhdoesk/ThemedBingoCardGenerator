package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component.CardScreenGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.CharactersChipsGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnAndWinnersGrids
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnCharacter
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.PlayersLazyRow
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun PortraitPlayerSessionScreen(
    state: SessionUiState.Success,
    participant: Participant?,
    card: List<BingoCharacter?>,
    event: (event: SessionUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
        modifier = Modifier
            .sizeIn(maxWidth = 600.dp)
            .padding(horizontal = 16.dp)
    ) {
        PlayersLazyRow(
            players = state.participants,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterVertically,
                space = 20.dp
            ),
            modifier = Modifier.fillMaxSize()
        ) {

            when (state.sessionState) {
                SessionState.NOT_STARTED -> {
                    if (card.size == 9) {
                        CardScreenGrid(
                            characters = card,
                            cardSize = CardSize.LARGE,
                            modifier = Modifier.fillMaxWidth())
                    }

                    Button(onClick = { event(SessionUiEvent.OnDrawNewCard) }) {
                        Text(text = stringResource(id = R.string.new_card))
                    }
                }

                else -> {
                    DrawnCharacter(
                        listOfCharacters = state.listOfDrawnCharacters,
                        maxPictureSize = 140.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    CharactersChipsGrid(
                        drawnCharacters = state.listOfDrawnCharacters.map { it.id },
                        card = card,
                        modifier = Modifier.fillMaxWidth(),
                        event = { event(it) },
                        state = state
                    )

                    DrawnAndWinnersGrids(
                        state = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 240.dp)
                    )
                }
            }
        }
    }
}