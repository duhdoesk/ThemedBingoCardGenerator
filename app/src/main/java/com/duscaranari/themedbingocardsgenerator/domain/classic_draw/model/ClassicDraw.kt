package com.duscaranari.themedbingocardsgenerator.domain.classic_draw.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "classic_draw_table")
data class ClassicDraw (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "draw_id")
    val drawId: Long = 0L,

    @ColumnInfo(name = "numbers")
    val numbers: Int,

    @ColumnInfo(name = "drawn_numbers")
    val drawnNumbers: String,

    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean = false
)