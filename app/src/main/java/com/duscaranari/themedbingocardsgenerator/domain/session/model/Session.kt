package com.duscaranari.themedbingocardsgenerator.domain.session.model

import com.duscaranari.themedbingocardsgenerator.domain.user.model.Host

data class Session(
    val id: String = "",
    val name: String = "",
    val state: SessionState = SessionState.NOT_STARTED,
    val locked: Boolean = false,
    val password: String? = null,
    val themeId: String = "",
    val listOfDrawnCharactersIds: List<String> = emptyList(),
    val limitOfWinners: Int = 1,
    val host: Host? = null
)