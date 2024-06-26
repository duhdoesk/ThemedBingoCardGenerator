package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event

import androidx.navigation.NavController
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.CreateSessionViewModel

fun createSessionScreenEventHandler(
    event: CreateSessionEvent,
    viewModel: CreateSessionViewModel,
    navController: NavController
) {
    when (event) {
        is CreateSessionEvent.OnCreateNewSession -> {
            val sessionId = viewModel.onCreateNewSession()
            navController.popBackStack()
            navController.navigate("${AppScreens.Session.name}/$sessionId")
        }

        is CreateSessionEvent.OnUpdateName ->
            viewModel.onUpdateName(event.name)

        is CreateSessionEvent.OnUpdatePassword ->
            viewModel.onUpdatePassword(event.password)

        is CreateSessionEvent.OnUpdateLockState ->
            viewModel.onUpdateLocked(event.locked)

        is CreateSessionEvent.OnUpdateThemeId ->
            viewModel.onUpdateThemeId(event.themeId)

        is CreateSessionEvent.OnUpdateLimitOfWinners ->
            viewModel.onUpdateLimitOfWinners(event.limit)
    }
}