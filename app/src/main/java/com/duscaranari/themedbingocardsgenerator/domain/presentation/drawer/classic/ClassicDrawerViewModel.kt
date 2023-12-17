package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic

import androidx.lifecycle.ViewModel
import com.duscaranari.themedbingocardsgenerator.domain.repository.ClassicDrawRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ClassicDrawerViewModel @Inject constructor(private val classicDrawRepository: ClassicDrawRepository)
    : ViewModel() {

    private val _uiState = MutableStateFlow<ClassicDrawerUiState>(ClassicDrawerUiState.Loading)
    val uiState = _uiState.asStateFlow()
}