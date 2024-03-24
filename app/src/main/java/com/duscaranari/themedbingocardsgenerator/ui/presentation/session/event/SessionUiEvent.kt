package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event

import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.SessionViewModel

sealed class SessionUiEvent {
    data object OnStartDrawing : SessionUiEvent()
    data object OnFinishSession: SessionUiEvent()
    data object OnDrawNextCharacter: SessionUiEvent()
}

fun handleSessionScreenEvent(
    event: SessionUiEvent,
    viewModel: SessionViewModel
) {

    when (event) {
        is SessionUiEvent.OnStartDrawing ->
            viewModel.startDrawing()

        is SessionUiEvent.OnFinishSession -> {
            /*todo*/
        }

        is SessionUiEvent.OnDrawNextCharacter -> {
            /*todo*/
        }
    }
}