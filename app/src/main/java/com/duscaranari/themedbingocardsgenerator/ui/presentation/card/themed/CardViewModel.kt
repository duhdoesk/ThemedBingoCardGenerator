package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.domain.character.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import com.duscaranari.themedbingocardsgenerator.domain.user.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val characterRepository: CharacterRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _cardUiState = MutableStateFlow<CardUiState>(CardUiState.Loading)
    val cardUiState = _cardUiState.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = getAllThemes()

            if (themes.isEmpty()) {
                _cardUiState.value = CardUiState.Error(R.string.unbable_to_load)
            } else {
                _cardUiState.value = CardUiState.PendingTheme(getAllThemes())
            }
        }
    }

    fun resetState() {
        loadInitialData()
    }

    fun selectTheme(themeId: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val theme = getThemeById(themeId)
            val characters = getThemeCharacters(themeId)
            val user = getCurrentUser()?.userName.orEmpty()

            if (theme != null && characters != null) {

                _cardUiState.value = CardUiState.Success(
                    currentTheme = theme,
                    themeCharacters = characters,
                    currentUser = user,
                    drawnCharacters = characters.shuffled().subList(0, 9)
                )
            }

            else {
                _cardUiState.value = CardUiState.Error(R.string.unbable_to_load)
            }
        }
    }

    fun drawNewCard() {

        when (val state = cardUiState.value) {
            is CardUiState.Success -> {
                _cardUiState.update {
                    state.copy(
                        drawnCharacters = shuffleCharacters(
                            state.themeCharacters,
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
            setCurrentUser(
                User(
                    userId = "1",
                    userName = userName
                )
            )

            when (val state = cardUiState.value) {
                is CardUiState.Success -> {
                    _cardUiState.update {
                        state.copy(
                            currentUser = getCurrentUser()?.userName.orEmpty()
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
            }

            else -> return
        }
    }

    fun onChangeCardSize(boolean: Boolean) {

        when (val state = cardUiState.value) {
            is CardUiState.Success -> {
                _cardUiState.update {
                    state.copy(
                        cardSize = if (boolean) CardSize.LARGE else CardSize.MEDIUM
                    )
                }
            }

            else -> return
        }

        drawNewCard()
    }

    private fun shuffleCharacters(characters: List<Character>, amount: Int): List<Character> {
        return characters.shuffled().subList(0, amount)
    }

    private suspend fun getThemeById(themeId: String): Theme? {
        return themeRepository.getThemeById(themeId)
    }

    private suspend fun getThemeCharacters(themeId: String): List<Character>? {
        return characterRepository.getThemeCharacters(themeId)
    }

    private suspend fun getCurrentUser(userId: String = "1"): User? {
        return userRepository.getUser(userId)
    }

    private suspend fun setCurrentUser(user: User) {
        userRepository.insertUser(user)
    }

    private suspend fun getAllThemes(): List<Theme> {
        return themeRepository.getAllThemesOrderById()
    }
}