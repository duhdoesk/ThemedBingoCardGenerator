package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.event

sealed class ClassicCardUiEvent {
    data object OnDrawNewCard: ClassicCardUiEvent()
    data class OnUpdateCurrentUser(val user: String): ClassicCardUiEvent()
}