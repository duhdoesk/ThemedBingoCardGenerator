package com.duscaranari.themedbingocardsgenerator.network

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelperImpl (private val apiService: ApiService) : ApiHelper {

    override fun getAppData() = flow {
        emit(apiService.getAppData())
    }
}