package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.landscape.LandscapePlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.portrait.PortraitPlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun PlayerSessionScreen(
    state: SessionUiState.Success,
    participant: Participant?,
    card: List<BingoCharacter?>,
    event: (event: SessionUiEvent) -> Unit
) {
    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitPlayerSessionScreen(
                state = state,
                participant = participant,
                card = card
            ) { event(it) }

        else ->
            LandscapePlayerSessionScreen(
                state = state,
                participant = participant,
                card = card
            ) { event(it) }
    }
}