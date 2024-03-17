package com.duscaranari.themedbingocardsgenerator.data.network.firestore.model

import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.google.firebase.firestore.DocumentId

class SessionDTO(
    @DocumentId val id: String = "",
    val name: String = "",
    val state: String = "",
    val locked: Boolean = false,
    val password: String? = null,
    val themeId: String = "",
    val listOfDrawnCharactersIds: List<String> = emptyList(),
    val listOfWinnersIds: List<String> = emptyList(),
    val limitOfWinners: Int = 1
) {

    fun toObject(): Session {
        val state = when (this.state) {
            "NOT_STARTED" -> SessionState.NOT_STARTED
            "DRAWING" -> SessionState.DRAWING
            else -> SessionState.FINISHED
        }

        return this.run {
            Session(
                id = id,
                name = name,
                state = state,
                locked = locked,
                password = password,
                themeId = themeId,
                listOfDrawnCharactersIds = listOfDrawnCharactersIds,
                listOfWinnersIds = listOfWinnersIds,
                limitOfWinners = limitOfWinners,
            )
        }
    }

    fun fromObject(session: Session): SessionDTO =
        session.run {
            SessionDTO(
                name = name,
                state = state.name,
                locked = locked,
                password = password,
                themeId = themeId,
                listOfDrawnCharactersIds = listOfDrawnCharactersIds,
                listOfWinnersIds = listOfWinnersIds,
                limitOfWinners = limitOfWinners
            )
        }
}

fun mockSession() =
    SessionDTO(
        id = "orci",
        name = "Tigresas",
        state = "NOT_STARTED",
        locked = true,
        password = null,
        themeId = "ut",
        listOfDrawnCharactersIds = listOf(),
        listOfWinnersIds = listOf(),
        limitOfWinners = 12,
    )