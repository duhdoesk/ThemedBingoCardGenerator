package com.duscaranari.themedbingocardsgenerator.domain.theme.model

import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter

data class BingoTheme (
    val name: String,
    val picture: String,
    val characters: List<BingoCharacter>
)