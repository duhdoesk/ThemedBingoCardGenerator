package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.landscape

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed.DrawerLazyGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.ButtonsRow
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnCharacter
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.PlayersLazyRow
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun LandscapeHostSessionScreenDrawing(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Log.d("STATE", state.toString())

    Column(
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp
        ),
        modifier = Modifier
            .sizeIn(maxHeight = 600.dp, maxWidth = 1000.dp)
    ) {
        PlayersLazyRow(
            players = state.participants,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 24.dp
            ),
            verticalAlignment = Alignment.Top,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${stringResource(id = R.string.drawn)} (${state.listOfDrawnCharacters.size}/${state.characters.size})",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )

                DrawerLazyGrid(
                    characters = state.listOfDrawnCharacters.reversed(),
                    onClick = { },
                    modifier = Modifier.fillMaxHeight(),
                    columns = GridCells.Fixed(1)
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1.6f)
                    .fillMaxHeight()
            ) {
                DrawnCharacter(
                    listOfCharacters = state.listOfDrawnCharacters,
                    maxPictureSize = 240.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                if (state.sessionState == SessionState.FINISHED) {
                    OutlinedButton(
                        enabled = false,
                        onClick = { }
                    ) {
                        Text(text = stringResource(id = R.string.session_finished))
                    }
                }
                else {
                    ButtonsRow { event(it) }
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${stringResource(id = R.string.winners_card)} (${state.listOfWinners.size}/${state.limitOfWinners})",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxHeight()
                ) {
                    itemsIndexed(state.listOfWinners) { index, winner ->
                        OutlinedCard {
                            Text(
                                text = "${index + 1} - ${winner.name}",
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}