package com.duscaranari.themedbingocardsgenerator.domain.participant.model

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.ParticipantDTO

class Participant(
    val id: String,
    val name: String,
    val picture: String,
    val card: List<String>
) {

    fun toDTO() =
        this.run {
            ParticipantDTO(
                id = id,
                name = name,
                picture = picture,
                card = card
            )
        }
}

fun mockParticipant() =
    Participant(
        id = "GDYGDQDQPMCM",
        name = "Dana Pacheco",
        picture = "arcu",
        card = listOf(
            "SASUDG",
            "KDOADF",
            "DKASODKA",
            "DAGSYDA",
            "PYTOK",
            "KTEOREWQ",
            "FJRIGHQPWIHGQ",
            "JIPGJPIJG",
            "DKPOJFIH4QNPVMVQDLPQQ"
        )
    )