package com.duscaranari.themedbingocardsgenerator.presentation.card

import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

sealed class CardState {

    object Loading : CardState()

    data class Ready(
        var currentTheme: Theme,
        var themeCharacters: List<Character>,
        var drawnCharacters: List<Character>,
        var currentUser: String = ""
    ) : CardState()
}
