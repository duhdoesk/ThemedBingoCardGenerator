package com.duscaranari.themedbingocardsgenerator.domain.presentation.card

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.model.User
import com.duscaranari.themedbingocardsgenerator.domain.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.ThemeRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val characterRepository: CharacterRepository,
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cardState = MutableStateFlow<CardState>(CardState.Loading)
    val cardState = _cardState.asStateFlow()

    init { loadData() }

    private fun loadData() {

        val themeId = checkNotNull(savedStateHandle["themeId"]).toString()

        viewModelScope.launch(Dispatchers.IO) {

            val theme = getThemeById(themeId)
            val characters = getThemeCharacters(themeId)
            val user = getCurrentUser()

            if (theme != null && characters != null) {
                _cardState.value = CardState.Ready(
                    currentTheme = theme,
                    themeCharacters = characters,
                    drawnCharacters = shuffleCharacters(characters),
                    currentUser = user?.userName.orEmpty()
                )
            }
        }
    }

    fun drawNewCard() {

        when (val state = cardState.value) {
            is CardState.Ready -> {
                _cardState.value = state.copy(
                    drawnCharacters = shuffleCharacters(state.themeCharacters)
                )
            }

            else -> { loadData() }
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

            when (val state = cardState.value) {
                is CardState.Ready -> {
                    _cardState.value = state.copy(
                        currentUser = getCurrentUser()?.userName.orEmpty()
                    )
                }

                else -> { loadData() }
            }
        }
    }

    private fun shuffleCharacters(characters: List<Character>): List<Character> {
        return characters.shuffled().subList(0, 9)
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
}