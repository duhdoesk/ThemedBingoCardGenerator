package com.duscaranari.themedbingocardsgenerator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme

@Dao
interface ThemeDao {

//    QUERY

    @Query("SELECT * from theme_table order by theme_id ASC")
    suspend fun getAllThemesOrderById(): List<Theme>

    @Query("SELECT * from theme_table order by theme_name ASC")
    suspend fun getAllThemesOrderByName(): List<Theme>

    @Query("SELECT * FROM theme_table where theme_name like '%' || :themeName || '%' order by theme_id ASC")
    suspend fun getThemesByNameOrderById(themeName: String): List<Theme>

    @Query("SELECT * FROM theme_table where theme_name like '%' || :themeName || '%' order by theme_name ASC")
    suspend fun getThemesByNameOrderByName(themeName: String): List<Theme>

    @Query("SELECT * from theme_table where theme_id=:themeId limit 1")
    suspend fun getThemeById(themeId: String): Theme?


//    DELETE

    @Query("DELETE from theme_table")
    suspend fun clearThemeTable()


//    INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(theme: Theme)
}