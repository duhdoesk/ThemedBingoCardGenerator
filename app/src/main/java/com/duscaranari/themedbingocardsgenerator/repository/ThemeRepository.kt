package com.duscaranari.themedbingocardsgenerator.repository

import com.duscaranari.themedbingocardsgenerator.data.ThemeDao
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val themeDao: ThemeDao
) {

    suspend fun getAllThemes() =
        themeDao.getAllThemes()

    suspend fun getThemeById(themeId: String) =
        themeDao.getThemeById(themeId)
}