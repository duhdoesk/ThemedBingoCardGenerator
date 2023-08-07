package com.duscaranari.themedbingocardsgenerator.network

import com.duscaranari.themedbingocardsgenerator.model.AppData
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getAppData(): Flow<AppData>
}