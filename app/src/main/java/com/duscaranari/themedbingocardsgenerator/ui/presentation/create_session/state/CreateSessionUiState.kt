package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state

data class CreateSessionUiState(
    val name: String = "",
    val nameErrors: List<String> = emptyList(),
    val locked: Boolean = false,
    val password: String = "",
    val passwordErrors: List<String> = emptyList(),
    val themeId: String = "1"
)