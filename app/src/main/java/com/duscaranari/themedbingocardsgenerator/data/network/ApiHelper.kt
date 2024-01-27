package com.duscaranari.themedbingocardsgenerator.data.network

import com.duscaranari.themedbingocardsgenerator.data.network.model.DataNetworkEntity
import retrofit2.Response

interface ApiHelper {

    suspend fun getAppData(): Response<DataNetworkEntity>
}