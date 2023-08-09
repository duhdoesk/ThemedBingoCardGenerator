package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

@Dao
interface ThemeDao {

    @Query("SELECT * from theme_table")
    suspend fun getAllThemes() : List<Theme>

    @Query("SELECT * from theme_table where theme_id=:themeId")
    suspend fun getThemeById(themeId: String) : Theme?
}