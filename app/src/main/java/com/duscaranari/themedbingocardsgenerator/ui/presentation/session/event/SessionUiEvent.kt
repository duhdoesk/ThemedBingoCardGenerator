package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event

import android.content.Context
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.SessionViewModel

sealed class SessionUiEvent {
    data object OnStartDrawing : SessionUiEvent()
}

fun handleSessionScreenEvent(
    event: SessionUiEvent,
    viewModel: SessionViewModel
) {

    when (event) {
        is SessionUiEvent.OnStartDrawing ->
            viewModel.startDrawing()
    }
}