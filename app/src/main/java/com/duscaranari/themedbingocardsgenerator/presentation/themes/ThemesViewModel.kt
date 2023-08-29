package com.duscaranari.themedbingocardsgenerator.presentation.themes

import androidx.lifecycle.SavedStateHandle
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
    private val domainModelMapper: DomainModelMapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _themesState = MutableStateFlow<ThemesState>(ThemesState.Loading)
    val themesState = _themesState.asStateFlow()

    val screen = checkNotNull(savedStateHandle["screen"]).toString()

    init {
        checkData()
    }

    fun checkData() {

        viewModelScope.launch {

            var networkData: DataNetworkEntity? = try {
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
            if both return null, we show "no internet connection" message.
             */
            else if (localData == null && networkData == null) {
                _themesState.value = ThemesState.NoData
                return@launch
            }

            /*
            if only local data returns null, we update it
             */
            else if (localData == null && networkData != null) {
                updateLocalData(networkData)
            }

            /*
            then we load local data - when we have it
             */
            loadThemes()
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

    private suspend fun loadThemes() {
        val themes = themeRepository.getAllThemes()
        _themesState.value = ThemesState.Ready(themes)
    }
}