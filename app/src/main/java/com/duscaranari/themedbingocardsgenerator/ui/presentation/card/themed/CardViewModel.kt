package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.character.use_case.GetThemeCharactersUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllBingoThemesUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllThemesUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetThemeByIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User
import com.duscaranari.themedbingocardsgenerator.domain.user.use_case.GetUserUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.use_case.SetUserUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val setUserUseCase: SetUserUseCase,
    getAllBingoThemesUseCase: GetAllBingoThemesUseCase

) : ViewModel() {

    private val _themes = getAllBingoThemesUseCase.invoke()
    val themes = _themes.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    private val _cardUiState = MutableStateFlow<CardUiState>(CardUiState.Loading)
    val cardUiState = _cardUiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (themes.value.isEmpty()) {
                true -> _cardUiState.value = CardUiState.Error(R.string.unbable_to_load)
                else -> _cardUiState.value = CardUiState.PendingTheme(themes.value)
            }
        }
    }

    fun resetState() {
        loadInitialData()
    }

    fun selectTheme(theme: BingoTheme) {
        viewModelScope.launch(Dispatchers.IO) {

            val characters = theme.characters
            val user = getUserUseCase("1")?.userName.orEmpty()

            _cardUiState.value = CardUiState.Success(
                currentTheme = theme,
                currentUser = user,
                drawnCharacters = characters.shuffled().subList(0, 9)
            )
        }
    }

    fun drawNewCard() {
        when (val state = cardUiState.value) {
            is CardUiState.Success -> {
                _cardUiState.update {
                    state.copy(
                        drawnCharacters = shuffleCharacters(
                            state.currentTheme.characters,
                            state.cardSize.characterAmount
                        )
                    )
                }
            }
            else -> return
        }
    }

    fun updateCurrentUser(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            setUserUseCase(
                User(
                    userId = "1",
                    userName = userName
                )
            )

            when (val state = cardUiState.value) {
                is CardUiState.Success -> {
                    _cardUiState.update {
                        state.copy(
                            currentUser = getUserUseCase("1")?.userName.orEmpty()
                        )
                    }
                }

                else -> return@launch
            }
        }
    }

    fun onChangeCardSize() {
        when (val state = cardUiState.value) {
            is CardUiState.Success -> {
                _cardUiState.update {
                    state.copy(
                        cardSize = state.cardSize.next()
                    )
                }

                drawNewCard()
            }

            else -> return
        }
    }

    private fun shuffleCharacters(characters: List<BingoCharacter>, amount: Int): List<BingoCharacter> {
        return characters.shuffled().subList(0, amount)
    }
}