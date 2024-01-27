package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state

import com.duscaranari.themedbingocardsgenerator.domain.character.Character
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme

sealed class CardUiState {

    data object Loading: CardUiState()

    data class Error(val errorMessage: Int) : CardUiState()

    data class PendingTheme(val themes: List<Theme>) : CardUiState()

    data class Success(
        val currentTheme: Theme,
        val themeCharacters: List<Character>,
        val drawnCharacters: List<Character>,
        val currentUser: String = "",
        val cardSize: CardSize = CardSize.LARGE
    ) : CardUiState()
}
