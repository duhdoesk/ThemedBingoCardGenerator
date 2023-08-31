package com.duscaranari.themedbingocardsgenerator.presentation.component

import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

fun getRawTheme(): Theme {
    return Theme(
        themeId = "1",
        themeName = "Tema de Teste",
        themePicture = "https://literaturaeducativa.com.br/wp-content/uploads/2018/09/os-bichos-e-o-governo-da-floresta.jpg"
    )
}

fun getRawListOfThemes() : List<Theme> {
    return List(9) {
        getRawTheme()
    }
}

fun getRawListOfCharacters(): List<Character> {

    val character = Character(
        characterThemeId = "1",
        characterId = "1",
        characterPicture = "https://cdn-icons-png.flaticon.com/512/6866/6866552.png",
        characterName = "Character",
        characterCardId = "1"
    )

    return List(9) { character }
}