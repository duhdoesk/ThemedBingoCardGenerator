package com.duscaranari.themedbingocardsgenerator.presentation.card

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val characterRepository: CharacterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _cardState = MutableStateFlow<CardState>(CardState.Loading)
    val cardState = _cardState.asStateFlow()

    private lateinit var currentTheme: Theme
    private var themeCharacters: List<Character> = emptyList()
    private var drawnCharacters: List<Character> = emptyList()

    init {
        val themeId = checkNotNull(savedStateHandle["themeId"]).toString()
        loadData(themeId)
    }

    private fun loadData(themeId: String) {

        viewModelScope.launch {

            themeRepository.getThemeById(themeId)?.let { currentTheme = it }
            themeCharacters = characterRepository.getThemeCharacters(themeId)
        }
            .invokeOnCompletion { drawNewCard() }
    }

    fun drawNewCard() {

        drawnCharacters = themeCharacters.shuffled().subList(0, 9)

        when (val state = cardState.value) {

            is CardState.Loading -> {
                _cardState.value = CardState.Ready(
                    currentTheme = currentTheme,
                    drawnCharacters = drawnCharacters
                )
            }

            is CardState.Ready -> {
                _cardState.value = state.copy(
                    drawnCharacters = drawnCharacters
                )
            }
        }
    }

    fun updateCurrentUser(user: String) {

        if (_cardState.value is CardState.Ready) {
            _cardState.value = (cardState.value as CardState.Ready).copy(
                currentUser = user
            )
        }
    }
}