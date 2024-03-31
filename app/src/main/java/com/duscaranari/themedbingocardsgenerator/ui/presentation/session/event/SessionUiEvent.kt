package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event

sealed class SessionUiEvent {
    data object OnStartDrawing : SessionUiEvent()
    data object OnFinishSession: SessionUiEvent()
    data object OnDrawNextCharacter: SessionUiEvent()
    data object OnDrawNewCard: SessionUiEvent()
}