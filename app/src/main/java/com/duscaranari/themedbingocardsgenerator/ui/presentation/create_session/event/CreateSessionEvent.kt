package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event

sealed class CreateSessionEvent {
    data class OnUpdateName(val name: String): CreateSessionEvent()
    data class OnUpdateLockState(val locked: Boolean): CreateSessionEvent()
    data class OnUpdatePassword(val password: String): CreateSessionEvent()
    data class OnUpdateThemeId(val themeId: String): CreateSessionEvent()
    data class OnUpdateLimitOfWinners(val limit: Float): CreateSessionEvent()
    data object OnCreateNewSession: CreateSessionEvent()
}