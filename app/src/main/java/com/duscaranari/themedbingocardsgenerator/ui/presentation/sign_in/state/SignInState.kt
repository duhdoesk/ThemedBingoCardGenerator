package com.duscaranari.themedbingocardsgenerator.ui.presentation.sign_in.state

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)