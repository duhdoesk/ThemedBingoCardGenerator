package com.duscaranari.themedbingocardsgenerator.domain.user.model

import com.google.firebase.firestore.DocumentId

data class Host(
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = ""
)