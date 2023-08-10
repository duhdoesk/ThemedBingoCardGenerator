package com.duscaranari.themedbingocardsgenerator.presentation.themes

import android.util.Log
import androidx.lifecycle.MutableLiveData
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
import com.duscaranari.themedbingocardsgenerator.network.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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

    private val networkData = MutableLiveData<DataNetworkEntity>()
    private val localData = MutableLiveData<AppData>()

    private var themesList: List<Theme> = emptyList()

    init {
        checkForUpdates()
    }

    private fun checkForUpdates() {

        if (!isUpdated()) { updateLocalData() }

        loadThemes()
    }

    private fun updateLocalData() {

        viewModelScope.launch(Dispatchers.IO) {

            networkData.value?.let { networkData ->

                insertAppData(domainModelMapper.mapAppDataFromNetworkEntity(networkData))

                networkData.appData.forEach { theme ->

                    insertThemes(listOf(domainModelMapper.mapThemeFromNetworkEntity(theme)))

                    insertCharacter(theme.characterNetworkEntities.map { character ->
                        domainModelMapper.mapCharacterFromNetworkEntity(character, theme)
                    })
                }
            }
        }
    }

    private suspend fun insertCharacter(characters: List<Character>) {
        characterRepository.insertCharacters(characters)
    }

    private suspend fun insertThemes(themes: List<Theme>) {
        themeRepository.insertThemes(themes)
    }

    private suspend fun insertAppData(appData: AppData) {
        appDataRepository.insertAppData(appData)
    }

    private fun isUpdated(): Boolean {

        getNetworkData()
        getLocalData()

        val networkDataVersion = networkData.value?.dataVersion
        val localDataVersion = localData.value?.dataVersion

        return networkDataVersion == localDataVersion
    }

    private fun getNetworkData() {

        viewModelScope.launch {

            networkRepository.getAppData().let {

                if (it.isSuccessful) {
                    Log.d("RETROFIT TEST", Resource.success(it.body()).toString())
                    networkData.postValue(it.body())
                } else {
                    Log.d("RETROFIT TEST", it.errorBody().toString())
                }
            }
        }
    }

    private fun getLocalData() {

        viewModelScope.launch {
            appDataRepository.getAppData().let {
                localData.postValue(it)
            }
        }
    }

    private fun loadThemes() {

        viewModelScope.launch {

            themesList = themeRepository.getAllThemes()
        }.invokeOnCompletion {

            _themesState.value = ThemesState.Ready(themesList)
        }
    }
}