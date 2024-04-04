package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state

import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme

sealed class SessionUiState {

    data object Loading : SessionUiState()
    data class Error(val message: Int) : SessionUiState()
    data class Success(
        val sessionName: String = "",
        val isHost: Boolean = false,
        val sessionState: SessionState = SessionState.NOT_STARTED,
        val participants: List<Participant> = emptyList(),
        val limitOfWinners: Int = 1,
        val listOfDrawnCharacters: List<BingoCharacter> = emptyList(),
        val listOfWinners: List<String> = emptyList(),
        val theme: BingoTheme,
        val characters: List<BingoCharacter>
    ) : SessionUiState()
}

fun mockSessionUiState() =
    SessionUiState.Success(
        sessionName = "Lyle Steele",
        isHost = false,
        sessionState = SessionState.DRAWING,
        participants = listOf(
            Participant(
                id = "populo",
                name = "Della Booth",
                picture = "https://lh3.googleusercontent.com/a/ACg8ocJRCxTnMYJ8YyCRcJhYeeJOiNdhwYfLhp6qGwVbEM3IPw=s96-c",
                card = listOf()
            ),
            Participant(
                id = "delenit",
                name = "Glenna Camacho",
                picture = "https://lh3.googleusercontent.com/a/ACg8ocJRCxTnMYJ8YyCRcJhYeeJOiNdhwYfLhp6qGwVbEM3IPw=s96-c",
                card = listOf()
            ),
            Participant(
                id = "voluptatibus",
                name = "Nicole Kirkland",
                picture = "https://lh3.googleusercontent.com/a/ACg8ocJRCxTnMYJ8YyCRcJhYeeJOiNdhwYfLhp6qGwVbEM3IPw=s96-c",
                card = listOf()
            )
        ),
        limitOfWinners = 7521,
        listOfDrawnCharacters = listOf(
            BingoCharacter(id = "luptatum", name = "Francis Albert", picture = "ludus"),
            BingoCharacter(id = "eius", name = "Antonia Wilkinson", picture = "vituperata"),
            BingoCharacter(id = "sem", name = "Dale Swanson", picture = "fermentum"),
            BingoCharacter(id = "omittam", name = "Clint Mejia", picture = "ultricies")
        ),
        listOfWinners = listOf(),
        theme = BingoTheme(
            id = "mucius",
            name = "Valarie Franks",
            picture = "torquent"
        ),
        characters = emptyList()
    )