package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic

import com.duscaranari.themedbingocardsgenerator.data.ClassicDrawDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ClassicDrawerViewModel @Inject constructor(private val classicDrawDao: ClassicDrawDao) {

    private val _uiState = MutableStateFlow<ClassicDrawerUiState>(ClassicDrawerUiState.Loading)
    val uiState = _uiState.asStateFlow()
}