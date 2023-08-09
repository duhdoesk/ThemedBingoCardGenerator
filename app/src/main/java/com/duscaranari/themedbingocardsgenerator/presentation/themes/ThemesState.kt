package com.duscaranari.themedbingocardsgenerator.presentation.themes

import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

sealed class ThemesState{
    object Loading: ThemesState()
    data class Ready(val themesList: List<Theme>): ThemesState()
}
