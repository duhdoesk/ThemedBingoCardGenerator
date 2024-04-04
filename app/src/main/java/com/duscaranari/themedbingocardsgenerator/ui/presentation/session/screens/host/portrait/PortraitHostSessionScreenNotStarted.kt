package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun PortraitHostSessionScreenNotStarted(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = Modifier
            .sizeIn(maxWidth = 600.dp)
    ) {

        HostSessionScreenCard(
            text =
            "${stringResource(id = R.string.session_card)}: ${state.sessionName}"
        )

        HostSessionScreenCard(
            text =
            "${stringResource(id = R.string.theme_card)}: ${state.theme.name}"
        )

        HostSessionScreenCard(
            text =
            "${stringResource(id = R.string.winners_card)}: ${state.limitOfWinners}"
        )

        HostSessionScreenCard(
            text =
            "${stringResource(id = R.string.players_card)}: ${state.participants.size}"
        )

        PlayersLazyColumn(
            players = state.participants,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth()
                .weight(1f)
        )

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