package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllThemesUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetThemesByNameUseCase
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
    private val getAllThemesUseCase: GetAllThemesUseCase,
    private val getThemesByNameUseCase: GetThemesByNameUseCase
) : ViewModel() {

    private val _themesScreenUiState =
        MutableStateFlow<ThemesScreenUiState>(ThemesScreenUiState.Loading)
    val themesScreenUiState = _themesScreenUiState.asStateFlow()

    init {
        getAllThemes()
    }

    fun getAllThemes() {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = getAllThemesUseCase.invoke(
                displayOrder = getDisplayOrder()
            )

            updateUiListOfThemes(themes)
        }
    }

    fun filterThemesByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val themes = getThemesByNameUseCase.invoke(
                name = name,
                displayOrder = getDisplayOrder()
            )

            updateUiListOfThemes(themes)
        }
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
                            themesDisplayOrder = ThemesDisplayOrder.ID
                        )
                    }
                }
            }
        }
    }

    private fun getDisplayOrder(): ThemesDisplayOrder {
        return when (val state = themesScreenUiState.value) {
            is ThemesScreenUiState.Success -> state.themesDisplayOrder
            else -> ThemesDisplayOrder.ID
        }
    }
}