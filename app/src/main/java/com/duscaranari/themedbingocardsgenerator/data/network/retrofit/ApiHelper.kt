package com.duscaranari.themedbingocardsgenerator.data.network.retrofit

import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.model.DataNetworkEntity
import retrofit2.Response

interface ApiHelper {

    suspend fun getAppData(): Response<DataNetworkEntity>
}