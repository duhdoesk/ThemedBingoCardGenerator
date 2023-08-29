package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.model.Draw

@Dao
interface DrawDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewDraw(draw: Draw) : Long

    @Query("SELECT * FROM draw_table WHERE draw_id = :drawId")
    suspend fun getDrawById(drawId: Long): Draw?

    @Query("UPDATE draw_table SET draw_completed = 1 WHERE draw_id = :drawId")
    suspend fun finishDraw(drawId: Long)

    @Query("SELECT * FROM draw_table WHERE draw_completed = 0 LIMIT 1")
    suspend fun getActiveDraw(): Draw?

    @Query("SELECT draw_theme_id FROM draw_table WHERE draw_id = :drawId")
    suspend fun getDrawThemeId(drawId: Long): String

    @Query("UPDATE draw_table SET draw_theme_id = :themeId WHERE draw_id = :drawId")
    suspend fun setDrawThemeId(drawId: Long, themeId: String)

    @Query("SELECT draw_characters_id FROM draw_table WHERE draw_id = :drawId")
    suspend fun getDrawnElementsIds(drawId: Long): String

    @Query("UPDATE draw_table SET draw_characters_id = :idList WHERE draw_id = :drawId")
    suspend fun setDrawnElementsIds(drawId: Long, idList: String)
}