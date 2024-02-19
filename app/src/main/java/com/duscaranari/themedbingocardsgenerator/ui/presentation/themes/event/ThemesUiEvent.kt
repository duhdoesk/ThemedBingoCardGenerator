package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.event

sealed class ThemesUiEvent {
    data class OnThemePick(val themeId: String): ThemesUiEvent()
    data class OnQueryChange(val query: String): ThemesUiEvent()
    data object OnDisplayOrderChange: ThemesUiEvent()
}