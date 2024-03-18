package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.PlayersLazyColumn
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun CompactPortraitSessionScreen(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (state.sessionState) {
            SessionState.NOT_STARTED -> {
                Text(
                    text = "Players: (${state.participants.size})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                PlayersLazyColumn(
                    players = state.participants,
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                )

                Button(
                    modifier = Modifier.width(200.dp),
                    onClick = { event(SessionUiEvent.OnStartDrawing) }
                ) {
                    Text(text = "Start drawing")
                }
            }

            SessionState.DRAWING -> {

            }

            SessionState.FINISHED -> {

            }
        }
    }
}