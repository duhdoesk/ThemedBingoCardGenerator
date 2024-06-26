package com.duscaranari.themedbingocardsgenerator.domain.user.model

import com.duscaranari.themedbingocardsgenerator.data.local.model.LocalUser
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.ParticipantDTO

class User(
    val id: String,
    val name: String,
    val picture: String,
    val card: List<String>
) {

    fun toLocalUser() =
        this.run {
            LocalUser(
                userId = id,
                userName = name
            )
        }

    fun toNetworkUser() =
        this.run {
            ParticipantDTO(
                id = id,
                name = name,
                picture = picture,
                card = card
            )
        }
}