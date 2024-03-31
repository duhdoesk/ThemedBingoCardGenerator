package com.duscaranari.themedbingocardsgenerator.ui.presentation.session

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.common.FinishDrawConfirmationDialog
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.HostOrPlayerSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.state.SessionUiState

@Composable
fun SessionScreen(
    sessionViewModel: SessionViewModel = hiltViewModel()
) {

    val state = sessionViewModel.sessionUiState
        .collectAsStateWithLifecycle()
        .value

    val participant = sessionViewModel.participant
        .collectAsStateWithLifecycle()
        .value

    val card = sessionViewModel.card
        .collectAsStateWithLifecycle()
        .value

    var showDialog by remember { mutableStateOf(false) }

    when (state) {

        is SessionUiState.Success -> {
            HostOrPlayerSessionScreen(
                state = state,
                participant = participant,
                card = card
            ) { event ->
                when (event) {
                    is SessionUiEvent.OnStartDrawing ->
                        sessionViewModel.startDrawing()

                    is SessionUiEvent.OnFinishSession ->
                        showDialog = true


                    is SessionUiEvent.OnDrawNextCharacter ->
                        sessionViewModel.drawNextCharacter()

                    is SessionUiEvent.OnDrawNewCard ->
                        sessionViewModel.drawNewCard()

                    is SessionUiEvent.OnAddWinner ->
                        sessionViewModel.addWinner()
                }
            }
        }

        is SessionUiState.Error ->
            ErrorScreen(
                errorMessage = state.message,
                onTryAgain = { })

        else ->
            LoadingScreen()
    }

    if (showDialog) {
        FinishDrawConfirmationDialog(
            onConfirm = {
                showDialog = false
                sessionViewModel.finishSession()
            },
            onDismiss = { showDialog = false }
        )
    }
}