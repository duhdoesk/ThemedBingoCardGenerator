package com.duscaranari.themedbingocardsgenerator.domain.classic_draw.use_case

import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.repository.ClassicDrawRepository
import javax.inject.Inject

class FinishClassicDrawUseCase @Inject constructor(private val classicDrawRepository: ClassicDrawRepository) {

    suspend operator fun invoke(classicDrawId: Long) =
        classicDrawRepository.finishDraw(classicDrawId)
}