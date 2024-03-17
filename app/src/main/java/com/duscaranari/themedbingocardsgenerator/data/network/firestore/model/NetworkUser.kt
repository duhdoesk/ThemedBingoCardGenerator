package com.duscaranari.themedbingocardsgenerator.data.network.firestore.model

import com.duscaranari.themedbingocardsgenerator.domain.user.model.User
import com.google.firebase.firestore.DocumentId

class NetworkUser(
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = "",
    val card: List<String> = emptyList()
) {

    fun toObject() =
        this.run {
            User(
                id = id,
                name = name,
                picture = picture,
                card = card
            )
        }
}
