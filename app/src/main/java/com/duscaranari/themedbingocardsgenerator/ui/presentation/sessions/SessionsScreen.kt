package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppScreens

@Composable
fun SessionsScreen(
    sessionsViewModel: SessionsViewModel = hiltViewModel(),
    navController: NavController
) {

    Column {
        val sessions = sessionsViewModel.sessions.collectAsStateWithLifecycle().value

        for (session in sessions) {
            Text(text = session.id)
        }

        Button(onClick = { navController.navigate(AppScreens.CreateSession.name) }) {
            Text(text = "Criar Sessão")
        }
    }
}