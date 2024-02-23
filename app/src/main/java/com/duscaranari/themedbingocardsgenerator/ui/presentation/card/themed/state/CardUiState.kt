package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state

import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme

sealed class CardUiState {

    data object Loading: CardUiState()

    data class Error(val errorMessage: Int) : CardUiState()

    data object PendingTheme : CardUiState()

    data class Success(
        val currentTheme: BingoTheme,
        val drawnCharacters: List<BingoCharacter>,
        val currentUser: String = "",
        val cardSize: CardSize = CardSize.LARGE
    ) : CardUiState()
}
