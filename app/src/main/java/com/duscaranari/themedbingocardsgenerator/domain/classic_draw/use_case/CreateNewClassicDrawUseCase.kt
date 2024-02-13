package com.duscaranari.themedbingocardsgenerator.domain.classic_draw.use_case

import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.model.ClassicDraw
import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.repository.ClassicDrawRepository
import javax.inject.Inject

class CreateNewClassicDrawUseCase @Inject constructor(private val classicDrawRepository: ClassicDrawRepository) {

    suspend operator fun invoke(classicDraw: ClassicDraw): Long =
        classicDrawRepository.createNewDraw(classicDraw)
}