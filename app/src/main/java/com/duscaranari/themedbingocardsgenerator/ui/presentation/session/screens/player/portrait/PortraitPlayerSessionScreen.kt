package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.model.mockListOfBingoCharacters
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.mockParticipant
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component.CardScreenGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.BingoButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.CharactersChipsGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnAndWinnersGrids
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnCharacter
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.PlayersLazyRow
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.mockSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

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
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .widthIn(max = 400.dp)
                    ) {
                        if (card.size == 9) {
                            CardScreenGrid(
                                characters = card,
                                cardSize = CardSize.LARGE,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        Spacer(Modifier.heightIn(12.dp))

                        Button(
                            onClick = { event(SessionUiEvent.OnDrawNewCard) },
                            modifier = Modifier.widthIn(max = 320.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.new_card),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                else -> {
                    if (state.sessionState == SessionState.FINISHED) {
                        Text(
                            text = stringResource(R.string.session_finished).uppercase(),
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth()
                        )
                    }

                    DrawnCharacter(
                        listOfCharacters = state.listOfDrawnCharacters,
                        maxPictureSize = 140.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    CharactersChipsGrid(
                        drawnCharacters = state.listOfDrawnCharacters.map { it.id },
                        card = card,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400.dp),
                        event = { event(it) },
                        state = state
                    )

                    BingoButton(
                        card = card,
                        drawnCharacters = state.listOfDrawnCharacters.map { it.id },
                        participantId = participant?.id,
                        winners = state.listOfWinners,
                        event = { event(it) },
                        modifier = Modifier.widthIn(max = 320.dp)
                    )

                    DrawnAndWinnersGrids(
                        state = state,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp)
                    )
                }
            }
        }
    }
}

@PortraitPreviews
@Composable
fun Preview() {
    PortraitPlayerSessionScreen(
        state = mockSessionUiState(),
        participant = mockParticipant(),
        card = mockListOfBingoCharacters(),
        event = {}
    )
}