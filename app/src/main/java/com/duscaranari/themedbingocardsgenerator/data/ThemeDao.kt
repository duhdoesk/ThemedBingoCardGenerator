package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

@Dao
interface ThemeDao {

//    QUERY

    @Query("SELECT * from theme_table")
    suspend fun getAllThemes() : List<Theme>

    @Query("SELECT * from theme_table where theme_id=:themeId")
    suspend fun getThemeById(themeId: String) : Theme?


//    DELETE

    @Query("DELETE from theme_table")
    suspend fun clearThemeTable()


//    INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(theme: Theme)
}