package com.duscaranari.themedbingocardsgenerator.data.network.model

import com.google.gson.annotations.SerializedName

data class CharacterNetworkEntity(

    @field:SerializedName("character_id")
    val characterId: String,

    @field:SerializedName("character_name")
    val characterName: String,

    @field:SerializedName("character_picture")
    val characterPicture: String
)