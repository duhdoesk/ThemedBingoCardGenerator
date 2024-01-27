package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.classic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.classic.screens.LandscapeClassicDrawerScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.classic.screens.PortraitClassicDrawerScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.common.FinishDrawConfirmationDialog
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun ClassicDrawerScreen(classicDrawerViewModel: ClassicDrawerViewModel = hiltViewModel()) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var showDialog by remember { mutableStateOf(false) }

    when (val state = classicDrawerViewModel.uiState.collectAsStateWithLifecycle().value) {
        is ClassicDrawerUiState.Loading ->
            LoadingScreen()

        is ClassicDrawerUiState.Success ->
            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Landscape ->

                    when (rememberWindowInfo().screenHeightInfo) {
                        is WindowInfo.WindowType.Compact -> {
                            RotateScreen()
                        }

                        else -> {
                            LandscapeClassicDrawerScreen(
                                uiState = state,
                                onDrawNextCharacter = { classicDrawerViewModel.drawNextNumber() },
                                onFinishDraw = { classicDrawerViewModel.finishDraw() },
                                onStartNewDraw = { classicDrawerViewModel.startNewDraw(75) },
                                onCopyDrawn = { clipboardManager.setText(
                                    AnnotatedString(classicDrawerViewModel.getStringOfDrawnNumbers())) }
                            )
                        }
                    }


                else ->
                    PortraitClassicDrawerScreen(
                        uiState = state,
                        onDrawNextCharacter = { classicDrawerViewModel.drawNextNumber() },
                        onFinishDraw = { classicDrawerViewModel.finishDraw() },
                        onStartNewDraw = { classicDrawerViewModel.startNewDraw(75) },
                        onCopyDrawn = { clipboardManager.setText(
                            AnnotatedString(classicDrawerViewModel.getStringOfDrawnNumbers())) }
                    )
            }

        else ->
            ErrorScreen(
                errorMessage = R.string.draw_error,
                onTryAgain = { })
    }

    if (showDialog) {
        FinishDrawConfirmationDialog(
            onDismiss = { showDialog = false },
            onConfirm = {
                classicDrawerViewModel.finishDraw()
                showDialog = false
            })
    }
}