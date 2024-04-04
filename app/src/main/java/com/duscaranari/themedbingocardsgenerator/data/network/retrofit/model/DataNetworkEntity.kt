package com.duscaranari.themedbingocardsgenerator.data.network.retrofit.model

import com.google.gson.annotations.SerializedName

data class DataNetworkEntity(

    @field:SerializedName("app_name")
    val appName: String,

    @field:SerializedName("app_data_version")
    val dataVersion: String,

    @field:SerializedName("app_data")
    val appData: List<ThemeNetworkEntity>,
)