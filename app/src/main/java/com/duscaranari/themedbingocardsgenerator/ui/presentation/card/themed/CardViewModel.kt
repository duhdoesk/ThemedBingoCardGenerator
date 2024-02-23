package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.use_case.GetCharactersFromThemeIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetBingoThemeByIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User
import com.duscaranari.themedbingocardsgenerator.domain.user.use_case.GetUserUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.use_case.SetUserUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val setUserUseCase: SetUserUseCase,
    getCharactersFromThemeIdUseCase: GetCharactersFromThemeIdUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    private val _cardSize = MutableStateFlow(CardSize.LARGE)
    private val _theme = MutableStateFlow<BingoTheme?>(null)
    private val _newCard = MutableStateFlow(false)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _characters = _theme
        .filterNotNull()
        .flatMapLatest {
            getCharactersFromThemeIdUseCase
                .invoke(it.id)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    private val _drawnCharacters = combine(
        _characters,
        _cardSize,
        _newCard
    ) { characters, cardSize, _ ->

        if (characters.isEmpty())
            emptyList()
        else
            characters.shuffled().subList(0, cardSize.characterAmount)
    }

    val uiState = combine(
        _user,
        _cardSize,
        _theme,
        _drawnCharacters
    ) { user, cardSize, theme, drawnCharacters ->

        when (theme) {
            null ->
                CardUiState.PendingTheme

            else -> {
                if (drawnCharacters.isEmpty()) {
                    CardUiState.Loading
                }
                else
                    CardUiState.Success(
                        currentTheme = theme,
                        drawnCharacters = drawnCharacters,
                        currentUser = user?.userName.orEmpty(),
                        cardSize = cardSize
                    )
            }
        }
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            CardUiState.Loading
        )

    init {
        getUserName()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            _user.update { getUserUseCase.invoke("1") }
        }
    }

    fun onResetState() {
        _theme.update { null }
    }

    fun onSelectTheme(theme: BingoTheme) {
        _theme.update { theme }
    }

    fun onDrawNewCard() {
        _newCard.update { !it }
    }

    fun onUpdateCurrentUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setUserUseCase(
                User(
                    userId = "1",
                    userName = userName
                )
            )

            getUserName()
        }
    }

    fun onChangeCardSize() {
        when (val state = uiState.value) {
            is CardUiState.Success -> {
                _cardSize.update { state.cardSize.next() }
            }

            else -> return
        }
    }
}