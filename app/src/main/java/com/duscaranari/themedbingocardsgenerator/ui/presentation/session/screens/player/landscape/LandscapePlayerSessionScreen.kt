package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.player.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun LandscapePlayerSessionScreen(
    state: SessionUiState.Success,
    participant: Participant?,
    card: List<BingoCharacter?>,
    event: (event: SessionUiEvent) -> Unit
) {

}