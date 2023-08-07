package com.duscaranari.themedbingocardsgenerator.model

import com.google.gson.annotations.SerializedName

data class ThemeCharacter(

    @field:SerializedName("character_id")
    val characterId: String,

    @field:SerializedName("character_name")
    val characterName: String,

    @field:SerializedName("character_picture")
    val characterPicture: String
)