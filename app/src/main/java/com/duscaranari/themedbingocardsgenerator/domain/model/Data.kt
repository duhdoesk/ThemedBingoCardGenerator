package com.duscaranari.themedbingocardsgenerator.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class Data (

    @PrimaryKey
    @ColumnInfo(name = "app_name")
    val appName: String,

    @ColumnInfo(name = "data_version")
    val dataVersion: String
)