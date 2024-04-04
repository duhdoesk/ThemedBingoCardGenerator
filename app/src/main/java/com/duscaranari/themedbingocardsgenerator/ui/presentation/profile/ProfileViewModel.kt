package com.duscaranari.themedbingocardsgenerator.ui.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authHelper: AuthHelper) : ViewModel() {

    val googleUser = authHelper.getSignedInUser()

    fun onSignOut() {
        viewModelScope.launch(Dispatchers.IO) { authHelper.signOut() }
    }
}