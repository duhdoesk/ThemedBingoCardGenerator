package com.duscaranari.themedbingocardsgenerator.network

import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getAppData() = apiHelper.getAppData()
}