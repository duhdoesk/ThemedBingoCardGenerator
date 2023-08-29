package com.duscaranari.themedbingocardsgenerator.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draw_table")
data class Draw(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "draw_id")
    val drawId: Long = 0L,

    @ColumnInfo(name = "draw_theme_id")
    val themeId: String,

    @ColumnInfo(name = "draw_characters_id")
    val drawnCharactersIdList: String,

    @ColumnInfo(name = "draw_completed")
    val drawCompleted: Boolean = false
)
