package com.duscaranari.themedbingocardsgenerator.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme_table")
data class Theme(

    @PrimaryKey
    @ColumnInfo(name = "theme_id")
    val themeId: String,

    @ColumnInfo(name = "theme_name")
    val themeName: String,

    @ColumnInfo(name = "theme_picture")
    val themePicture: String
)