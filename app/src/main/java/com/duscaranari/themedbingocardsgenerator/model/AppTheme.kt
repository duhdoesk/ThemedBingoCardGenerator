package com.duscaranari.themedbingocardsgenerator.model

import com.google.gson.annotations.SerializedName

data class AppTheme(

    @field:SerializedName("theme_characters")
    val themeCharacters: List<ThemeCharacter>,

    @field:SerializedName("theme_id")
    val themeId: String,

    @field:SerializedName("theme_name")
    val themeName: String,

    @field:SerializedName("theme_picture")
    val themePicture: String
)