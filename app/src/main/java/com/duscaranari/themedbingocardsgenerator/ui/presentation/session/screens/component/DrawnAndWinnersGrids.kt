package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed.DrawerLazyGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun DrawnAndWinnersGrids(
    state: SessionUiState.Success,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            alignment = Alignment.Start,
            space = 4.dp
        ),
        modifier = modifier
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${stringResource(id = R.string.drawn)} (${state.listOfDrawnCharacters.size})",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )

            DrawerLazyGrid(
                characters = state.listOfDrawnCharacters,
                onClick = { }
            )
        }

        VerticalDivider()

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "${stringResource(id = R.string.winners_card)} (${state.listOfWinners.size})",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
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