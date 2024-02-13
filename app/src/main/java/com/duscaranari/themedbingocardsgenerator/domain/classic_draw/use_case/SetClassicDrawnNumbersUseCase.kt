package com.duscaranari.themedbingocardsgenerator.domain.classic_draw.use_case

import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.repository.ClassicDrawRepository
import javax.inject.Inject

class SetClassicDrawnNumbersUseCase @Inject constructor(private val classicDrawRepository: ClassicDrawRepository) {

    suspend operator fun invoke(
        classicDrawId: Long,
        drawnIds: String
    ) {
        classicDrawRepository.setDrawnNumbers(
            id = classicDrawId,
            numbers = drawnIds
        )
    }
}