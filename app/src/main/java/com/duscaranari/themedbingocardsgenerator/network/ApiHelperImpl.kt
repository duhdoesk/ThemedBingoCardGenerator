package com.duscaranari.themedbingocardsgenerator.network

import com.duscaranari.themedbingocardsgenerator.network.model.DataNetworkEntity
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getAppData(): Response<DataNetworkEntity> =
        apiService.getAppData()
}