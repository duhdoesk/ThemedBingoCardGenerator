package com.duscaranari.themedbingocardsgenerator.presentation.themes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.network.NetworkRepository
import com.duscaranari.themedbingocardsgenerator.network.util.Resource
import com.duscaranari.themedbingocardsgenerator.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemesViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _themesState = MutableStateFlow<ThemesState>(ThemesState.Loading)
    val themesState = _themesState.asStateFlow()

    private var themesList: List<Theme> = emptyList()

    private fun loadThemes() {

        viewModelScope.launch {

            themesList = themeRepository.getAllThemes()
        }.invokeOnCompletion {

            _themesState.value = ThemesState.Ready(themesList)
        }
    }

    private fun getNetworkData() {

        viewModelScope.launch {

            networkRepository.getAppData().let {

                if (it.isSuccessful) {
                    Log.d("RETROFIT TEST", Resource.success(it.body()).toString())
                } else {
                    Log.d("RETROFIT TEST", it.errorBody().toString())
                }
            }
        }
    }

    init {
        loadThemes()
        getNetworkData()
    }
}