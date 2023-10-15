package com.duscaranari.themedbingocardsgenerator.domain.presentation.characters

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _charactersList = MutableStateFlow<List<Character>>(emptyList())
    val charactersList = _charactersList.asStateFlow()

    init { setCharacters() }

    private fun setCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _charactersList.value = loadCharacters().orEmpty()
        }
    }

    private suspend fun loadCharacters(): List<Character>? {
        val themeId = checkNotNull(savedStateHandle["themeId"]).toString()
        return characterRepository.getThemeCharacters(themeId)
    }
}