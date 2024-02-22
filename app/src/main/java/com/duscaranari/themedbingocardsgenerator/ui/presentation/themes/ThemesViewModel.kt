package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllBingoThemesUseCase
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
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ThemesViewModel @Inject constructor(
    getAllBingoThemesUseCase: GetAllBingoThemesUseCase
) : ViewModel() {

    private val _themes = getAllBingoThemesUseCase.invoke()
    val themes = _themes.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _displayOrder = MutableStateFlow(ThemesDisplayOrder.NAME)

    val uiState = combine(
        query
            .debounce(500L)
            .distinctUntilChanged(),
        _displayOrder,
        themes
    ) { query, displayOrder, themes ->

        when (themes.isEmpty()) {
            true ->
                ThemesScreenUiState.Error(errorMessage = R.string.draw_error)

            else -> {
                if (query.isBlank()) {
                    ThemesScreenUiState.Success(
                        themes = sortThemes(themes),
                        themesDisplayOrder = displayOrder
                    )
                } else {
                    ThemesScreenUiState.Success(
                        themes = sortThemes(themes.filter { it.name.contains(query) }),
                        themesDisplayOrder = displayOrder
                    )
                }
            }
        }
    }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ThemesScreenUiState.Loading
        )

    fun onQueryChange(query: String) {
        _query.update { query }
        _isSearching.update { true }
    }

    fun onDisplayOrderChange() {
        _displayOrder.update {
            when (it) {
                ThemesDisplayOrder.ID -> ThemesDisplayOrder.NAME
                ThemesDisplayOrder.NAME -> ThemesDisplayOrder.ID
            }
        }
    }

    private fun sortThemes(themes: List<BingoTheme>): List<BingoTheme> {
        return if (_displayOrder.value == ThemesDisplayOrder.NAME) {
            themes.sortedBy { it.name }
        } else {
            themes.sortedBy { it.id }
        }
    }
}