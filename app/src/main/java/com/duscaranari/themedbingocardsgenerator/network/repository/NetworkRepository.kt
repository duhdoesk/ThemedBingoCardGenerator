package com.duscaranari.themedbingocardsgenerator.network.repository

import com.duscaranari.themedbingocardsgenerator.network.ApiHelper
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getAppData() = apiHelper.getAppData()
}