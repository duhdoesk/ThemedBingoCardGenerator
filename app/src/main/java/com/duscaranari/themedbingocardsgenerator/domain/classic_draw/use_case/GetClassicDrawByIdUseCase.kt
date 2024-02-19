package com.duscaranari.themedbingocardsgenerator.domain.classic_draw.use_case

import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.model.ClassicDraw
import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.repository.ClassicDrawRepository
import javax.inject.Inject

class GetClassicDrawByIdUseCase @Inject constructor(private val classicDrawRepository: ClassicDrawRepository) {

    suspend operator fun invoke(classicDrawId: Long): ClassicDraw? =
        classicDrawRepository.getDrawById(classicDrawId)
}