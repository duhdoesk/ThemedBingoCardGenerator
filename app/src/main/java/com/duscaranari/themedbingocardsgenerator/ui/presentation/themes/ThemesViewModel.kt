package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllThemesUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetThemesByNameUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ThemesViewModel @Inject constructor(
    private val getAllThemesUseCase: GetAllThemesUseCase,
    private val getThemesByNameUseCase: GetThemesByNameUseCase
) : ViewModel() {

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _displayOrder = MutableStateFlow(ThemesDisplayOrder.ID)

    val uiState = combine(
        query
            .debounce(500L)
            .distinctUntilChanged(),
        _displayOrder
    ) { query, displayOrder ->

        if (query.isBlank()) {
            ThemesScreenUiState.Success(
                themes = getThemes(),
                themesDisplayOrder = displayOrder
            )
        } else {
            ThemesScreenUiState.Success(
                themes = getThemesByName(query),
                themesDisplayOrder = displayOrder
            )
        }
    }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ThemesScreenUiState.Loading
        )

    fun onQueryChange(query: String) {
        _query.value = query
        _isSearching.update { true }
    }

    fun onChangeDisplayOrder() {
        Log.d("CALLED", "onChangeDisplayOrder")
        _displayOrder.update {
            when (_displayOrder.value) {
                ThemesDisplayOrder.ID -> ThemesDisplayOrder.NAME
                ThemesDisplayOrder.NAME -> ThemesDisplayOrder.ID
            }
        }
    }

    private fun getThemes(): List<Theme> {
        return runBlocking {
            getAllThemesUseCase.invoke(displayOrder = _displayOrder.value)
        }
    }

    private fun getThemesByName(name: String): List<Theme> {
        return runBlocking {
            getThemesByNameUseCase.invoke(
                name = name,
                displayOrder = _displayOrder.value
            )
        }
    }
}