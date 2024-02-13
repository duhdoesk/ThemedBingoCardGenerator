package com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case

import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.model.Draw
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.repository.DrawRepository
import javax.inject.Inject

class CreateNewThemedDrawUseCase @Inject constructor(private val drawRepository: DrawRepository) {

    suspend operator fun invoke(draw: Draw): Long =
        drawRepository.createNewDraw(draw)
}