package com.duscaranari.themedbingocardsgenerator.domain.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class Character(

    @PrimaryKey
    @ColumnInfo(name = "character_id")
    val characterId: String,

    @ColumnInfo(name = "character_theme_id")
    val characterThemeId: String,

    @ColumnInfo(name = "character_name")
    val characterName: String,

    @ColumnInfo(name = "character_picture")
    val characterPicture: String,

    @ColumnInfo(name = "character_card_id")
    val characterCardId: String
)
