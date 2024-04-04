package com.duscaranari.themedbingocardsgenerator.data.network.retrofit.model

import com.google.gson.annotations.SerializedName

data class ThemeNetworkEntity(

    @field:SerializedName("theme_characters")
    val characterNetworkEntities: List<CharacterNetworkEntity>,

    @field:SerializedName("theme_id")
    val themeId: String,

    @field:SerializedName("theme_name")
    val themeName: String,

    @field:SerializedName("theme_picture")
    val themePicture: String
)