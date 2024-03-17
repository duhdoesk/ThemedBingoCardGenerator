package com.duscaranari.themedbingocardsgenerator.domain.session.model

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO

data class Session(
    val id: String = "",
    val name: String = "",
    val host: String = "",
    val state: SessionState = SessionState.NOT_STARTED,
    val locked: Boolean = false,
    val password: String? = null,
    val themeId: String = "",
    val listOfDrawnCharactersIds: List<String> = emptyList(),
    val listOfWinnersIds: List<String> = emptyList(),
    val limitOfWinners: Int = 1,
) {

    fun toDTO(): SessionDTO =
        this.run {
            SessionDTO(
                name = name,
                host = host,
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