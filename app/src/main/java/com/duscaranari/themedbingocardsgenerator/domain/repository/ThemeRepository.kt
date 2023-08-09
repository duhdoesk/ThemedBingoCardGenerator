package com.duscaranari.themedbingocardsgenerator.domain.repository

import com.duscaranari.themedbingocardsgenerator.data.ThemeDao
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val themeDao: ThemeDao
) {

    suspend fun getAllThemes() =
        themeDao.getAllThemes()

    suspend fun getThemeById(themeId: String) =
        themeDao.getThemeById(themeId)

    suspend fun clearThemeTable() =
        themeDao.clearThemeTable()

    suspend fun insertThemes(themes: List<Theme>) =
        themeDao.insertThemes(themes)
}