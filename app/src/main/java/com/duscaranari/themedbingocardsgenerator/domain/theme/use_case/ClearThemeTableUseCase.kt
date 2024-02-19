package com.duscaranari.themedbingocardsgenerator.domain.theme.use_case

import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import javax.inject.Inject

class ClearThemeTableUseCase @Inject constructor(private val themeRepository: ThemeRepository) {

    suspend operator fun invoke() {
        themeRepository.clearThemeTable()
    }
}