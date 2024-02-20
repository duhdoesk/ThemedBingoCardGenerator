package com.duscaranari.themedbingocardsgenerator.data.network.retrofit.repository

import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.ApiHelper
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getAppData() = apiHelper.getAppData()
}