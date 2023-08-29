package com.duscaranari.themedbingocardsgenerator.domain.repository

import com.duscaranari.themedbingocardsgenerator.data.DrawDao
import com.duscaranari.themedbingocardsgenerator.domain.model.Draw
import javax.inject.Inject

class DrawRepository @Inject constructor(
    private val drawDao: DrawDao
) {

    suspend fun createNewDraw(draw: Draw): Long =
        drawDao.createNewDraw(draw)

    suspend fun finishDraw(drawId: Long) =
        drawDao.finishDraw(drawId)

    suspend fun getActiveDraw(): Draw? =
        drawDao.getActiveDraw()

    suspend fun getDrawThemeId(drawId: Long): String =
        drawDao.getDrawThemeId(drawId)

    suspend fun setDrawThemeId(drawId: Long, themeId: String) =
        drawDao.setDrawThemeId(drawId, themeId)

    suspend fun getDrawnElementsIds(drawId: Long): String =
        drawDao.getDrawnElementsIds(drawId)

    suspend fun setDrawnElementsIds(drawId: Long, drawnCharactersIds: String) =
        drawDao.setDrawnElementsIds(drawId, drawnCharactersIds)
}