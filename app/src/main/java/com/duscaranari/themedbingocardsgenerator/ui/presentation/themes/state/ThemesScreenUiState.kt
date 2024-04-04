package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme

sealed class ThemesScreenUiState {

    data object Loading: ThemesScreenUiState()

    data class Error(val errorMessage: Int) : ThemesScreenUiState()

    data class Success(
        val themes: List<BingoTheme>,
        val themesDisplayOrder: ThemesDisplayOrder
    ) : ThemesScreenUiState()
}