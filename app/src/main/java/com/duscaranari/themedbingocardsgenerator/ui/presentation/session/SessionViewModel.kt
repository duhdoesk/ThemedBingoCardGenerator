package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.use_case.GetCharactersFromThemeIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.participant.model.Participant
import com.duscaranari.themedbingocardsgenerator.domain.participant.use_case.DrawNewCardUseCase
import com.duscaranari.themedbingocardsgenerator.domain.participant.use_case.GetParticipantFromSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.participant.use_case.GetParticipantsFromSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.DrawNextCharacterUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.FinishSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetSessionByIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.StartDrawingUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetBingoThemeByIdUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    getSessionByIdUseCase: GetSessionByIdUseCase,
    getParticipantsFromSessionUseCase: GetParticipantsFromSessionUseCase,
    getParticipantFromSessionUseCase: GetParticipantFromSessionUseCase,
    savedStateHandle: SavedStateHandle,
    authHelper: AuthHelper,
    private val getBingoThemeByIdUseCase: GetBingoThemeByIdUseCase,
    private val getCharactersFromThemeByIdUseCase: GetCharactersFromThemeIdUseCase,
    private val startDrawingUseCase: StartDrawingUseCase,
    private val finishSessionUseCase: FinishSessionUseCase,
    private val drawNextCharacterUseCase: DrawNextCharacterUseCase,
    private val drawNewCardUseCase: DrawNewCardUseCase
) : ViewModel() {

    private val googleUser = authHelper.getSignedInUser()

    private val _theme = MutableStateFlow<BingoTheme?>(null)
    val theme = _theme.asStateFlow()

    private val _characters = MutableStateFlow<List<BingoCharacter>>(emptyList())
    val characters = _characters.asStateFlow()

    private val _session = getSessionByIdUseCase
        .invoke(checkNotNull(savedStateHandle["sessionId"]))
        .map { it?.toObject() }
        .onEach { session ->
            session?.let {
                _theme.update {
                    getBingoThemeByIdUseCase.invoke(session.themeId).first()
                }

                _characters.update {
                    getCharactersFromThemeByIdUseCase.invoke(session.themeId).first()
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    private val _participants = getParticipantsFromSessionUseCase
        .invoke(checkNotNull(savedStateHandle["sessionId"]))
        .map { list ->
            list.map { participant ->
                participant.toObject()
            }
        }

    val participants = _participants
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    private val _participant = getParticipantFromSessionUseCase.invoke(
        sessionId = checkNotNull(savedStateHandle["sessionId"]),
        participantId = googleUser?.id ?: ""
    )
        .map { it?.toObject() }

    val participant = _participant
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    val card = combine(
        _characters, _participant
    ) { char, part ->
        part?.card?.map { string ->
            char.find { it.id == string }
        } ?: emptyList<BingoCharacter>()
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList<BingoCharacter>()
        )

    val sessionUiState = combine(
        _session, _participants
    ) { session, participants ->

        when (session) {
            null ->
                SessionUiState.Loading

            else -> {
                googleUser?.let { user ->
                    _theme.value?.let { theme ->

                        val drawnCharacters = mutableListOf<BingoCharacter>()

                        session.listOfDrawnCharactersIds.forEach { characterId ->
                            _characters.value.find { it.id == characterId }?.let { character ->
                                drawnCharacters.add(character)
                            }
                        }

                        val listOfWinners = mutableListOf<Participant>()

                        val players = participants
                            .filterNot { it.id == session.host }

                        session.listOfWinnersIds.forEach { winnerId ->
                            players.find { it.id == winnerId }?.let { winner ->
                                listOfWinners.add(winner)
                            }
                        }

                        val isHost = (user.id == session.host)
                        val allDrawn = (drawnCharacters.size == _characters.value.size)
                        val allWins = (session.listOfWinnersIds.size == session.limitOfWinners)
                        val state =
                            if (allDrawn || allWins) SessionState.FINISHED else session.state

                        SessionUiState.Success(
                            sessionName = session.name,
                            isHost = isHost,
                            sessionState = state,
                            participants = players,
                            limitOfWinners = session.limitOfWinners,
                            listOfDrawnCharacters = drawnCharacters,
                            listOfWinners = listOfWinners,
                            theme = theme,
                            characters = _characters.value
                        )
                    }
                }
            }
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            SessionUiState.Loading
        )

    fun startDrawing() =
        _session.value?.let {
            startDrawingUseCase.invoke(it.id)
        }

    fun finishSession() =
        _session.value?.let {
            finishSessionUseCase.invoke(it.id)
        }

    fun drawNextCharacter() {
        _session.value?.let { session ->
            val availableCharacters = _characters.value.filterNot {
                it.id in session.listOfDrawnCharactersIds
            }

            if (availableCharacters.isNotEmpty()) {
                val nextCharacter = availableCharacters.shuffled().first()
                drawNextCharacterUseCase.invoke(session.id, nextCharacter.id)
            }
        }
    }

    fun drawNewCard() {
        _session.value?.let { session ->
            participant.value?.let { participantNotNull ->

                val newCard = _characters.value.shuffled().subList(0, 9)

                drawNewCardUseCase.invoke(
                    sessionId = session.id,
                    participantId = participantNotNull.id,
                    card = newCard
                )
            }
        }
    }
}