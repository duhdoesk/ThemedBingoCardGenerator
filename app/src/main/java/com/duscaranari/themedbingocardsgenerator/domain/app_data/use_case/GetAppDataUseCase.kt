package com.duscaranari.themedbingocardsgenerator.domain.app_data.use_case

import com.duscaranari.themedbingocardsgenerator.domain.app_data.model.AppData
import com.duscaranari.themedbingocardsgenerator.domain.app_data.repository.AppDataRepository
import javax.inject.Inject

class GetAppDataUseCase @Inject constructor(private val appDataRepository: AppDataRepository) {

    suspend operator fun invoke(): AppData? =
        appDataRepository.getAppData()
}