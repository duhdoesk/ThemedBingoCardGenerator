package com.duscaranari.themedbingocardsgenerator.domain.user.model

import com.google.firebase.firestore.DocumentId

data class Participant(
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = "",
    val card: List<String> = emptyList()
)
