package com.duscaranari.themedbingocardsgenerator.domain.theme.use_case

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import javax.inject.Inject

class GetAllThemesUseCase @Inject constructor(private val themeRepository: ThemeRepository) {

    suspend operator fun invoke(displayOrder: ThemesDisplayOrder): List<Theme> {
        return when (displayOrder) {
            ThemesDisplayOrder.ID -> themeRepository.getAllThemesOrderById()
            ThemesDisplayOrder.NAME -> themeRepository.getAllThemesOrderByName()
        }
    }
}