package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.LandscapeClassicDrawerScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.PortraitClassicDrawerScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.common.FinishDrawConfirmationDialog
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun ClassicDrawerScreen(classicDrawerViewModel: ClassicDrawerViewModel = hiltViewModel()) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    var showDialog by remember { mutableStateOf(false) }

    when (val state = classicDrawerViewModel.uiState.collectAsState().value) {
        is ClassicDrawerUiState.Loading ->
            LoadingScreen()

        is ClassicDrawerUiState.Error ->
            ErrorScreen(
                errorMessage = R.string.draw_error,
                onTryAgain = { })

        else ->
            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Landscape ->
                    LandscapeClassicDrawerScreen()

                else ->
                    PortraitClassicDrawerScreen()
            }
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


/**
 * PORTRAIT PREVIEWS
 */
@PortraitPreviews
@Composable
fun PortraitPreview() {
    PortraitClassicDrawerScreen()
}


/**
 * LANDSCAPE PREVIEWS
 */
@PortraitPreviews
@Composable
fun LandscapePreview() {
    LandscapeClassicDrawerScreen()
}