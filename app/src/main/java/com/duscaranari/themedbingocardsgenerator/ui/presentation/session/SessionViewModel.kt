package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetSessionByIdUseCase
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    getSessionByIdUseCase: GetSessionByIdUseCase,
    savedStateHandle: SavedStateHandle,
    authHelper: AuthHelper
): ViewModel() {

    val session = getSessionByIdUseCase
        .invoke(checkNotNull(savedStateHandle["sessionId"]))
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    val googleUser = authHelper.getSignedInUser()
}