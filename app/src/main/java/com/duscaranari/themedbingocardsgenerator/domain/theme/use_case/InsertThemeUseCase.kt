package com.duscaranari.themedbingocardsgenerator.domain.theme.use_case

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import javax.inject.Inject

class InsertThemeUseCase @Inject constructor(private val themeRepository: ThemeRepository) {

    suspend operator fun invoke(theme: Theme) {
        themeRepository.insertThemes(theme)
    }
}