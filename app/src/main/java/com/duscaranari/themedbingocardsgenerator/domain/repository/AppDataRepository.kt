package com.duscaranari.themedbingocardsgenerator.domain.repository

import com.duscaranari.themedbingocardsgenerator.data.AppDataDao
import com.duscaranari.themedbingocardsgenerator.domain.model.AppData
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val appDataDao: AppDataDao
) {

    suspend fun getAppData(): AppData? =
        appDataDao.getAppData()

    suspend fun insertAppData(appData: AppData) =
        appDataDao.insertAppData(appData)
}