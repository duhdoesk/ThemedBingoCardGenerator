package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun SessionScreen(
    sessionViewModel: SessionViewModel = hiltViewModel()
) {

    val session = sessionViewModel.sessionUiState
        .collectAsStateWithLifecycle()
        .value

    when (session) {

        is SessionUiState.Success -> {
            Column {
                Text(text = session.sessionName)
                Text(text = session.isHost.toString())
                Text(text = session.limitOfWinners.toString())
                Text(text = session.state.name)
                Text(text = session.participants.firstOrNull()?.name ?: "name")
            }
        }

        else ->
            LoadingScreen()
    }
}