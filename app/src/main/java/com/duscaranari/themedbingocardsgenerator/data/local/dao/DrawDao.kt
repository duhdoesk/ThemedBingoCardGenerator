package com.duscaranari.themedbingocardsgenerator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.model.Draw

@Dao
interface DrawDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewDraw(draw: Draw) : Long

    @Query("SELECT * FROM draw_table WHERE draw_id = :drawId")
    suspend fun getDrawById(drawId: Long): Draw?

    @Query("UPDATE draw_table SET draw_completed = 1 WHERE draw_id = :drawId")
    suspend fun finishDraw(drawId: Long)

    @Query("SELECT * FROM draw_table ORDER BY draw_id DESC LIMIT 1")
    suspend fun getLastDraw(): Draw?

    @Query("SELECT draw_characters_id FROM draw_table WHERE draw_id = :drawId")
    suspend fun getDrawnElementsIds(drawId: Long): String

    @Query("UPDATE draw_table SET draw_characters_id = :idList WHERE draw_id = :drawId")
    suspend fun setDrawnElementsIds(drawId: Long, idList: String)
}