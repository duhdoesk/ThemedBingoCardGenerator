package com.duscaranari.themedbingocardsgenerator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.ClassicDraw

@Dao
interface ClassicDrawDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewDraw(draw: ClassicDraw) : Long

    @Query("SELECT * FROM classic_draw_table WHERE draw_id = :drawId")
    suspend fun getDrawById(drawId: Long): ClassicDraw?

    @Query("SELECT * FROM classic_draw_table ORDER BY draw_id DESC LIMIT 1")
    suspend fun getLastDraw(): ClassicDraw?

    @Query("UPDATE classic_draw_table SET is_completed = 1 WHERE draw_id = :drawId")
    suspend fun finishDraw(drawId: Long)

    @Query("SELECT drawn_numbers FROM classic_draw_table WHERE draw_id = :drawId")
    suspend fun getDrawnNumbers(drawId: Long): String

    @Query("UPDATE classic_draw_table SET drawn_numbers = :numbers WHERE draw_id = :drawId")
    suspend fun setDrawnNumbers(drawId: Long, numbers: String)
}