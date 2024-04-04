package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.HostSessionScreenCard
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.PlayersLazyColumn
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun LandscapeHostSessionScreenNotStarted(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            space = 24.dp
        ),
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .sizeIn(maxHeight = 600.dp, maxWidth = 1000.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.Top,
                space = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            HostSessionScreenCard(
                text =
                "${stringResource(id = R.string.players_card)}: ${state.participants.size}",
                modifier = Modifier
                    .height(60.dp)
            )

            PlayersLazyColumn(
                players = state.participants,
                columns = GridCells.Adaptive(minSize = 200.dp),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.Top,
                space = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            HostSessionScreenCard(
                text =
                "${stringResource(id = R.string.session_card)}: ${state.sessionName}",
                modifier = Modifier
                    .height(60.dp)
            )

            HostSessionScreenCard(
                text =
                "${stringResource(id = R.string.theme_card)}: ${state.theme.name}",
                modifier = Modifier
                    .height(60.dp)
            )

            HostSessionScreenCard(
                text =
                "${stringResource(id = R.string.winners_card)}: ${state.limitOfWinners}",
                modifier = Modifier
                    .height(60.dp)
            )

            Spacer(modifier = Modifier.heightIn(60.dp))

            Button(
                modifier = Modifier.width(200.dp),
                onClick = { event(SessionUiEvent.OnStartDrawing) }
            ) {
                Text(text = stringResource(id = R.string.start_drawing_button))
            }

            TextButton(
                modifier = Modifier.width(200.dp),
                onClick = { event(SessionUiEvent.OnStartDrawing) }
            ) {
                Text(text = stringResource(id = R.string.close_session_button))
            }
        }
    }
}