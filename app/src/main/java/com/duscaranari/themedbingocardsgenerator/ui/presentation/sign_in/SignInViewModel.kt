package com.duscaranari.themedbingocardsgenerator.ui.presentation.sign_in

import android.app.appsearch.AppSearchResult.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.ui.presentation.about.Logo
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sign_in.state.SignInState
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import com.duscaranari.themedbingocardsgenerator.util.auth.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        Log.d("SIGN IN", result.data.toString())
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}