package com.duscaranari.themedbingocardsgenerator.domain.app_data.repository

import com.duscaranari.themedbingocardsgenerator.data.local.dao.AppDataDao
import com.duscaranari.themedbingocardsgenerator.domain.app_data.model.AppData
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val appDataDao: AppDataDao
) {

    suspend fun getAppData(): AppData? =
        appDataDao.getAppData()

    suspend fun insertAppData(appData: AppData) =
        appDataDao.insertAppData(appData)
}