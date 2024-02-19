package com.duscaranari.themedbingocardsgenerator.domain.theme.use_case

import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import javax.inject.Inject

class GetThemeByIdUseCase @Inject constructor(private val themeRepository: ThemeRepository) {

    suspend operator fun invoke(themeId: String): Theme? {
        return themeRepository.getThemeById(themeId)
    }
}