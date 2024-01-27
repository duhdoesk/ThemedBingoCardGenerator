package com.duscaranari.themedbingocardsgenerator.domain.themed_draw

import com.duscaranari.themedbingocardsgenerator.data.local.dao.DrawDao
import javax.inject.Inject

class DrawRepository @Inject constructor(
    private val drawDao: DrawDao
) {

    suspend fun createNewDraw(draw: Draw): Long =
        drawDao.createNewDraw(draw)

    suspend fun getDrawById(drawId: Long): Draw? =
        drawDao.getDrawById(drawId)

    suspend fun finishDraw(drawId: Long) =
        drawDao.finishDraw(drawId)

    suspend fun getLastDraw(): Draw? =
        drawDao.getLastDraw()

    suspend fun getDrawnElementsIds(drawId: Long): String =
        drawDao.getDrawnElementsIds(drawId)

    suspend fun setDrawnElementsIds(drawId: Long, drawnCharactersIds: String) =
        drawDao.setDrawnElementsIds(drawId, drawnCharactersIds)
}