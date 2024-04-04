package com.duscaranari.themedbingocardsgenerator.domain.theme.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.ThemeRepository
import javax.inject.Inject

class GetAllBingoThemesUseCase @Inject constructor(private val themeRepository: ThemeRepository) {

    operator fun invoke() =
        themeRepository.getAllThemes()
}