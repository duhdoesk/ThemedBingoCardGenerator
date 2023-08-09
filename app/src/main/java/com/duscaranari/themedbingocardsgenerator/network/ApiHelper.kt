package com.duscaranari.themedbingocardsgenerator.network

import com.duscaranari.themedbingocardsgenerator.network.model.DataNetworkEntity
import retrofit2.Response

interface ApiHelper {

    suspend fun getAppData(): Response<DataNetworkEntity>
}