package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.character.use_case.GetCharactersFromThemeIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.model.SessionState
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetParticipantsFromSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetSessionByIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetBingoThemeByIdUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    getSessionByIdUseCase: GetSessionByIdUseCase,
    getParticipantsFromSessionUseCase: GetParticipantsFromSessionUseCase,
    savedStateHandle: SavedStateHandle,
    authHelper: AuthHelper,
    private val getBingoThemeByIdUseCase: GetBingoThemeByIdUseCase,
    private val getCharactersFromThemeByIdUseCase: GetCharactersFromThemeIdUseCase
) : ViewModel() {

    private val _session = getSessionByIdUseCase
        .invoke(checkNotNull(savedStateHandle["sessionId"]))
        .map { it?.toObject() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    private val _participants = getParticipantsFromSessionUseCase
        .invoke(checkNotNull(savedStateHandle["sessionId"]))
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    private val googleUser = authHelper.getSignedInUser()

    val sessionUiState = combine(
        _session, _participants
    ) { session, participants ->

        when (session) {
            null ->
                SessionUiState.Loading

            else -> {
                googleUser?.let { user ->

                    val theme = getBingoThemeByIdUseCase
                        .invoke(session.themeId)
                        .first()

                    if (theme == null) {
                        SessionUiState.Loading
                    } else {

                        val drawnCharacters = getCharactersFromThemeByIdUseCase
                            .invoke(session.themeId)
                            .stateIn(
                                viewModelScope,
                                SharingStarted.WhileSubscribed(),
                                emptyList()
                            )

                        val isHost = (user.id == session.host)

                        val players = participants
                            ?.map { it.toObject() }
                            ?.filterNot { it.id == session.host }
                            ?: emptyList()

                        val listOfWinners = players.filter {
                            it.id in session.listOfWinnersIds
                        }

                        SessionUiState.Success(
                            sessionName = session.name,
                            isHost = isHost,
                            state = session.state,
                            participants = players,
                            limitOfWinners = session.limitOfWinners,
                            listOfDrawnCharacters = drawnCharacters.value,
                            listOfWinners = listOfWinners,
                            theme = theme
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
}