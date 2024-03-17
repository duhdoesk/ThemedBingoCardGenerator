package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state

import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User

sealed class SessionUiState {

    data object Loading : SessionUiState()
    data class Error(val message: Int) : SessionUiState()
    data class Success(
        val sessionName: String = "",
        val isHost: Boolean = false,
        val state: SessionState = SessionState.NOT_STARTED,
        val participants: List<User> = emptyList(),
        val limitOfWinners: Int = 1,
        val listOfDrawnCharacters: List<BingoCharacter> = emptyList(),
        val listOfWinners: List<User> = emptyList(),
        val theme: BingoTheme
    ) : SessionUiState()
}

fun mockSessionUiState() =
    SessionUiState.Success(
        sessionName = "Lyle Steele",
        isHost = false,
        state = SessionState.DRAWING,
        participants = listOf(),
        limitOfWinners = 7521,
        listOfDrawnCharacters = listOf(),
        listOfWinners = listOf(),
        theme = BingoTheme(
            id = "mucius",
            name = "Valarie Franks",
            picture = "torquent"
        )
    )