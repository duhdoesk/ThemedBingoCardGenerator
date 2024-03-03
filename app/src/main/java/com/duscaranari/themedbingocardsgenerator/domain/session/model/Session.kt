package com.duscaranari.themedbingocardsgenerator.domain.session.model

import com.duscaranari.themedbingocardsgenerator.domain.user.model.Host
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Participant
import com.google.firebase.firestore.DocumentId

data class Session(
    @DocumentId val id: String = "",
    val name: String = "",
    val state: String = "",
    val locked: Boolean = false,
    val password: String? = null,
    val themeId: String = "",
    val listOfDrawnCharactersIds: List<String> = emptyList(),
    val limitOfWinners: Int = 1,
    val host: Host? = null,
    val participants: List<Participant> = emptyList()
)

fun mockSession() =
    Session(
        id = "orci",
        name = "Tigresas",
        state = "NOT_STARTED",
        locked = true,
        password = null,
        themeId = "ut",
        listOfDrawnCharactersIds = listOf(),
        limitOfWinners = 12,
        host = null,
        participants = listOf()
    )