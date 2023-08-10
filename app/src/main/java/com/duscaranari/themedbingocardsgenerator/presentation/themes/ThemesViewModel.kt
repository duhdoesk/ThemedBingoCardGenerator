package com.duscaranari.themedbingocardsgenerator.presentation.themes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.network.util.DomainModelMapper
import com.duscaranari.themedbingocardsgenerator.domain.model.AppData
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.repository.AppDataRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.ThemeRepository
import com.duscaranari.themedbingocardsgenerator.network.model.DataNetworkEntity
import com.duscaranari.themedbingocardsgenerator.network.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ThemesViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val networkRepository: NetworkRepository,
    private val appDataRepository: AppDataRepository,
    private val characterRepository: CharacterRepository,
    private val domainModelMapper: DomainModelMapper
) : ViewModel() {

    private val _themesState = MutableStateFlow<ThemesState>(ThemesState.Loading)
    val themesState = _themesState.asStateFlow()

    init {
        checkForUpdates()
    }

    private fun checkForUpdates() {
        viewModelScope.launch {
            val networkData = getNetworkData()
            if (networkData == null) {
                loadThemes()
                return@launch
            }

            val localData = getLocalData()
            val isUpdated = isUpdated(networkData, localData)

            if (!isUpdated) {
                updateLocalData(networkData)
            }

            loadThemes()
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

    private fun isUpdated(networkData: DataNetworkEntity, localData: AppData): Boolean {
        val networkDataVersion = networkData.dataVersion
        val localDataVersion = localData.dataVersion

        return networkDataVersion == localDataVersion
    }

    private suspend fun getNetworkData(): DataNetworkEntity? {
        val response = networkRepository.getAppData()

        return if (response.isSuccessful) {
            response.body()
        } else null
    }

    private suspend fun getLocalData(): AppData {
        return appDataRepository.getAppData()
    }

    private fun loadThemes() {
        viewModelScope.launch {
            val themes = themeRepository.getAllThemes()
            _themesState.value = ThemesState.Ready(themes)
        }
    }

    private suspend fun clearDatabase() {
        themeRepository.clearThemeTable()
        characterRepository.clearCharactersTable()
    }
}