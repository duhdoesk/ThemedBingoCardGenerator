package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.host.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.ButtonsRow
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnAndWinnersGrids
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.DrawnCharacter
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component.PlayersLazyRow
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun PortraitHostSessionScreenDrawing(
    state: SessionUiState.Success,
    event: (event: SessionUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 20.dp),
        modifier = Modifier
            .sizeIn(maxWidth = 600.dp)
    ) {
        PlayersLazyRow(
            players = state.participants,
            modifier = Modifier.fillMaxWidth()
        )

        DrawnCharacter(
            listOfCharacters = state.listOfDrawnCharacters,
            maxPictureSize = 240.dp,
            modifier = Modifier
                .fillMaxWidth()
        )

        if (state.sessionState == SessionState.FINISHED)
            OutlinedButton(
                enabled = false,
                onClick = {  }
            ) {
                Text(text = stringResource(id = R.string.session_finished))
            }
        else
            ButtonsRow { event(it) }

        DrawnAndWinnersGrids(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 240.dp)
        )
    }
}