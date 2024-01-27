package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme

sealed class ThemesScreenUiState {

    data object Loading: ThemesScreenUiState()

    data class Error(val message: String) : ThemesScreenUiState()

    data class Success(
        val themes: List<Theme>,
        val themesDisplayFormat: ThemesDisplayFormat,
        val themesDisplayOrder: ThemesDisplayOrder
    ) : ThemesScreenUiState()
}