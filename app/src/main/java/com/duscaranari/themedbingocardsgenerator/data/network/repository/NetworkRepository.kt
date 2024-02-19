package com.duscaranari.themedbingocardsgenerator.data.network.repository

import com.duscaranari.themedbingocardsgenerator.data.network.ApiHelper
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getAppData() = apiHelper.getAppData()
}