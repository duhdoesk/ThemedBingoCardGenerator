package com.duscaranari.themedbingocardsgenerator.domain.theme.repository

import com.duscaranari.themedbingocardsgenerator.data.local.dao.ThemeDao
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import javax.inject.Inject

class ThemeRepository @Inject constructor(
    private val themeDao: ThemeDao
) {

    suspend fun getAllThemesOrderById() =
        themeDao.getAllThemesOrderById()

    suspend fun getAllThemesOrderByName() =
        themeDao.getAllThemesOrderByName()

    suspend fun getThemesByNameOrderById(name: String) =
        themeDao.getThemesByNameOrderById(name)

    suspend fun getThemesByNameOrderByName(name: String) =
        themeDao.getThemesByNameOrderByName(name)

    suspend fun getThemeById(themeId: String) =
        themeDao.getThemeById(themeId)

    suspend fun clearThemeTable() =
        themeDao.clearThemeTable()

    suspend fun insertThemes(theme: Theme) =
        themeDao.insert(theme)
}