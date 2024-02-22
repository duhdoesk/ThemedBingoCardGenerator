package com.duscaranari.themedbingocardsgenerator.domain.theme.model

import com.google.firebase.firestore.DocumentId

data class BingoTheme (
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = ""
)