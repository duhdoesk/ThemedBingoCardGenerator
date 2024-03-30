package com.duscaranari.themedbingocardsgenerator.data.network.firestore.model

import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.google.firebase.firestore.DocumentId

class ParticipantDTO(
    @DocumentId val id: String = "",
    val name: String = "",
    val picture: String = "",
    val card: List<String> = emptyList()
) {

    fun toObject() =
        this.run {
            Participant(
                id = id,
                name = name,
                picture = picture,
                card = card
            )
        }
}
