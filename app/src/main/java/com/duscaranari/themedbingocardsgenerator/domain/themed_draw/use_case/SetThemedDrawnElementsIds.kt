package com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case

import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.repository.DrawRepository
import javax.inject.Inject

class SetThemedDrawnElementsIds @Inject constructor(private val drawRepository: DrawRepository) {

    suspend operator fun invoke(
        drawId: Long,
        drawnCharactersIds: String
    ) {
        drawRepository.setDrawnElementsIds(
            drawId = drawId,
            drawnCharactersIds = drawnCharactersIds
        )
    }
}