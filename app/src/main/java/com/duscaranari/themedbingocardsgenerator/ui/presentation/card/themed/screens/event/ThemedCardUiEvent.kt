package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.event

sealed class ThemedCardUiEvent {
    data object OnDrawNewCard: ThemedCardUiEvent()
    data object OnNavigateToCharactersScreen: ThemedCardUiEvent()
    data object OnChangeCardSize: ThemedCardUiEvent()
    data class OnUpdateCurrentUser(val user: String): ThemedCardUiEvent()
}