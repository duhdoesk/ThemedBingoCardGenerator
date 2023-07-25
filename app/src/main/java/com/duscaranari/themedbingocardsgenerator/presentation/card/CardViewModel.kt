package com.duscaranari.themedbingocardsgenerator.presentation.card

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.model.Character
import com.duscaranari.themedbingocardsgenerator.model.Theme
import com.duscaranari.themedbingocardsgenerator.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.repository.ThemeRepository
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

    private var themesList: List<Theme> = emptyList()
    private lateinit var currentTheme: Theme
    private var themeCharacters: List<Character> = emptyList()
    private var drawnCharacters: List<Character> = emptyList()

    init {
        val themeId = checkNotNull(savedStateHandle["themeId"]).toString()
        loadData(themeId)
    }

    private fun loadData(themeId: String) {

        viewModelScope.launch {
            themesList = themeRepository.getAllThemes()
            themeCharacters = characterRepository.getThemeCharacters(themeId)
        }
            .invokeOnCompletion {
                themesList.find { theme -> theme.themeId == themeId }
                    ?.let { currentTheme = it }

                drawNewCard()
            }
    }

    fun drawNewCard() {

        drawnCharacters = themeCharacters.shuffled().subList(0, 9)

        _cardState.value = CardState.Ready(
            currentTheme = currentTheme,
            drawnCharacters = drawnCharacters
        )
    }
}