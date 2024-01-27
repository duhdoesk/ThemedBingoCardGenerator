package com.duscaranari.themedbingocardsgenerator.domain.classic_draw

import com.duscaranari.themedbingocardsgenerator.data.local.dao.ClassicDrawDao
import javax.inject.Inject

class ClassicDrawRepository @Inject constructor(
    private val classicDrawDao: ClassicDrawDao
) {

    suspend fun createNewDraw(draw: ClassicDraw): Long =
        classicDrawDao.createNewDraw(draw)

    suspend fun getDrawById(id: Long): ClassicDraw? =
        classicDrawDao.getDrawById(id)

    suspend fun getLastDraw(): ClassicDraw? =
        classicDrawDao.getLastDraw()

    suspend fun finishDraw(id: Long) =
        classicDrawDao.finishDraw(id)

    suspend fun getDrawnNumbers(id: Long): String =
        classicDrawDao.getDrawnNumbers(id)

    suspend fun setDrawnNumbers(id: Long, numbers: String) =
        classicDrawDao.setDrawnNumbers(id, numbers)
}