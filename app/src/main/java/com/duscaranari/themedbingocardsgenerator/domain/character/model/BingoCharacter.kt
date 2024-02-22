package com.duscaranari.themedbingocardsgenerator.domain.character.model

import com.google.firebase.firestore.DocumentId

data class BingoCharacter (
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = ""
)