package com.duscaranari.themedbingocardsgenerator.presentation.themes

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.model.Theme
import com.duscaranari.themedbingocardsgenerator.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemesViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _themesState = MutableStateFlow<ThemesState>(ThemesState.Loading)
    val themesState = _themesState.asStateFlow()

    private var themesList: List<Theme> = emptyList()

    private fun loadThemes() {
        viewModelScope.launch {
            themesList = themeRepository.getAllThemes()
        }.invokeOnCompletion {
            _themesState.value = ThemesState.Ready(themesList)
        }
    }

    init {
        loadThemes()
    }
}