package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic

sealed class ClassicCardUiState {
    object Loading: ClassicCardUiState()
    data class Ready(
        val numbers: List<Int>,
        val currentUser: String
    ): ClassicCardUiState()
}