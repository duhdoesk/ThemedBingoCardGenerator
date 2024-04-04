package com.duscaranari.themedbingocardsgenerator.data.network.retrofit

import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.model.DataNetworkEntity
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {

    override suspend fun getAppData(): Response<DataNetworkEntity> =
        apiService.getAppData()
}