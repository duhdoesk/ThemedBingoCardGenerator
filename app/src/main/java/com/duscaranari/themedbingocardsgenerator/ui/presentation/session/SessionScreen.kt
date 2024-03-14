package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen

@Composable
fun SessionScreen(
    sessionViewModel: SessionViewModel = hiltViewModel()
) {

    val session = sessionViewModel.session
        .collectAsStateWithLifecycle()
        .value

    when (session) {
        null ->
            LoadingScreen()

        else -> {
            Column {
                Text(text = session.name)
                Text(text = session.host?.name ?: "host")
                Text(text = session.state)
                Text(text = session.limitOfWinners.toString())
            }
        }
    }
}