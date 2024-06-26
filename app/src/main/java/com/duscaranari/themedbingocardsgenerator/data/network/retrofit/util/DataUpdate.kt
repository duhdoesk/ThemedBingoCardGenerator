package com.duscaranari.themedbingocardsgenerator.data.network.retrofit.util

import com.duscaranari.themedbingocardsgenerator.domain.app_data.model.AppData
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.app_data.repository.AppDataRepository
import com.duscaranari.themedbingocardsgenerator.domain.character.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.domain.theme.repository.ThemeRepository
import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.model.DataNetworkEntity
import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.repository.NetworkRepository
import com.duscaranari.themedbingocardsgenerator.util.funLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DataUpdate @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val networkRepository: NetworkRepository,
    private val appDataRepository: AppDataRepository,
    private val characterRepository: CharacterRepository,
    private val domainModelMapper: DomainModelMapper,
) {

    suspend fun checkForUpdates() {
        funLogger("MainActivity checkForUpdates")

        val networkData: DataNetworkEntity? = try {
            getNetworkData()
        } catch (e: Exception) {
            null
        }

        val localData: AppData? = getLocalData()

        /*
        if both return data, we check for updates
         */
        if (localData != null && networkData != null) {
            checkForUpdates(localData, networkData)
        }

        /*
        if only local data returns null, we update it
         */
        else if (localData == null && networkData != null) {
            updateLocalData(networkData)
        }
    }

    private suspend fun checkForUpdates(localData: AppData, networkData: DataNetworkEntity) {

        if (localData.dataVersion != networkData.dataVersion) {
            updateLocalData(networkData)
        }
    }

    private suspend fun updateLocalData(networkData: DataNetworkEntity) {

        withContext(Dispatchers.IO) {

            clearDatabase()

            insertAppData(domainModelMapper.mapAppDataFromNetworkEntity(networkData))

            networkData.appData.forEach { theme ->
                insertThemes(domainModelMapper.mapThemeFromNetworkEntity(theme))

                insertCharacter(theme.characterNetworkEntities.map { character ->
                    domainModelMapper.mapCharacterFromNetworkEntity(character, theme)
                })
            }
        }
    }

    private suspend fun insertCharacter(characters: List<Character>) {
        characterRepository.insertCharacters(characters)
    }

    private suspend fun insertThemes(theme: Theme) {
        themeRepository.insertThemes(theme)
    }

    private suspend fun insertAppData(appData: AppData) {
        appDataRepository.insertAppData(appData)
    }

    private suspend fun getNetworkData(): DataNetworkEntity? {
        val response = networkRepository.getAppData()

        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    private suspend fun getLocalData(): AppData? {
        return appDataRepository.getAppData()
    }

    private suspend fun clearDatabase() {
        themeRepository.clearThemeTable()
        characterRepository.clearCharactersTable()
    }
}