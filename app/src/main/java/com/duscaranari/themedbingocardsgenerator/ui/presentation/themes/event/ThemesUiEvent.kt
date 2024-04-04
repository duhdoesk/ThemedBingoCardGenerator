package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.event

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme

sealed class ThemesUiEvent {
    data class OnThemePick(val theme: BingoTheme): ThemesUiEvent()
    data class OnQueryChange(val query: String): ThemesUiEvent()
    data object OnDisplayOrderChange: ThemesUiEvent()
}