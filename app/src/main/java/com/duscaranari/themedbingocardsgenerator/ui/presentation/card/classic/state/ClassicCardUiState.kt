package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state

sealed class ClassicCardUiState {
    data object Loading: ClassicCardUiState()
    data class Ready(
        val numbers: List<Int>,
        val currentUser: String
    ): ClassicCardUiState()
}