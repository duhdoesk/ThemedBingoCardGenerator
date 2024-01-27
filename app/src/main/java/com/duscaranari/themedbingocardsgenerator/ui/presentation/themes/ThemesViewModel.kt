package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayFormat
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemesViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _themesScreenUiState =
        MutableStateFlow<ThemesScreenUiState>(ThemesScreenUiState.Loading)
    val themesScreenUiState = _themesScreenUiState.asStateFlow()

    init {
        orderThemesById()
    }

    private fun updateUiListOfThemes(themes: List<Theme>) {
        if (themes.isEmpty()) {
            _themesScreenUiState.update {
                ThemesScreenUiState.Error(
                    message = "Unable to load data"
                )
            }
        } else {
            when (val state = themesScreenUiState.value) {
                is ThemesScreenUiState.Success -> {
                    _themesScreenUiState.update {
                        state.copy(
                            themes = themes
                        )
                    }
                }
                else -> {
                    _themesScreenUiState.update {
                        ThemesScreenUiState.Success(
                            themes = themes,
                            themesDisplayFormat = ThemesDisplayFormat.GRID,
                            themesDisplayOrder = ThemesDisplayOrder.ID
                        )
                    }
                }
            }
        }
    }

    private fun updateUiDisplayFormat(format: ThemesDisplayFormat) {
        when (val state = themesScreenUiState.value) {
            is ThemesScreenUiState.Success -> {
                _themesScreenUiState.update {
                    state.copy(
                        themesDisplayFormat = format
                    )
                }
            }
            else -> return
        }
    }

    fun orderThemesById() {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = getThemesOrderedById()
            updateUiListOfThemes(themes)
        }
    }

    fun orderThemesByName() {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = getThemesOrderedByName()
            updateUiListOfThemes(themes)
        }
    }

    fun filterThemesByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = getThemeByName(name)
            updateUiListOfThemes(themes)
        }
    }

    private suspend fun getThemesOrderedById(): List<Theme> {
        TODO()
    }

    private suspend fun getThemesOrderedByName(): List<Theme> {
        TODO()
    }

    private suspend fun getThemeByName(name: String): List<Theme> {
        TODO()
    }
}