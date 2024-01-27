package com.duscaranari.themedbingocardsgenerator.domain.app_data

import com.duscaranari.themedbingocardsgenerator.data.local.dao.AppDataDao
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val appDataDao: AppDataDao
) {

    suspend fun getAppData(): AppData? =
        appDataDao.getAppData()

    suspend fun insertAppData(appData: AppData) =
        appDataDao.insertAppData(appData)
}