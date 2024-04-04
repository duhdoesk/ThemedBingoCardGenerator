package com.duscaranari.themedbingocardsgenerator.domain.theme.model

import com.google.firebase.firestore.DocumentId

data class BingoTheme (
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = ""
)

fun mockBingoTheme() =
    BingoTheme(id = "bibendum", name = "Bichos", picture = "https://i.imgur.com/K71gi6P.jpg")