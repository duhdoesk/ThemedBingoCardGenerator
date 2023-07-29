package com.duscaranari.themedbingocardsgenerator.presentation.card

import com.duscaranari.themedbingocardsgenerator.model.Character
import com.duscaranari.themedbingocardsgenerator.model.Theme

sealed class CardState {

    object Loading : CardState()

    data class Ready(
        val currentTheme: Theme,
        val drawnCharacters: List<Character>,
        val currentUser: String = ""
    ) : CardState()
}
