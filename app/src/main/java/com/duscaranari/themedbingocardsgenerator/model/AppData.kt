package com.duscaranari.themedbingocardsgenerator.model

import com.google.gson.annotations.SerializedName

data class AppData(

    @field:SerializedName("app_data")
    val appData: List<AppTheme>,

    @field:SerializedName("app_data_version")
    val dataVersion: String,

    @field:SerializedName("app_name")
    val appName: String
)