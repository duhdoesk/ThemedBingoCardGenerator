package com.duscaranari.themedbingocardsgenerator.domain.character.model

import com.google.firebase.firestore.DocumentId

data class BingoCharacter (
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = ""
)

fun mockListOfBingoCharacters() : List<BingoCharacter>{
    return List(9) {
        BingoCharacter(id = "neglegentur", name = "Dora Wynn", picture = "nisi")
    }
}