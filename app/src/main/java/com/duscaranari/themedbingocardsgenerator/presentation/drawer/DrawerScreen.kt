package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun DrawerScreen(
    navController: NavHostController,
    drawerViewModel: DrawerViewModel = hiltViewModel()
) {

    when (val state = drawerViewModel.uiState.collectAsState().value) {

        is DrawerUiState.Loading ->
            LoadingScreen()

        is DrawerUiState.Error ->
            ErrorScreen(
                errorMessage = state.errorMessage,
                onTryAgain = { drawerViewModel.checkSavedState() }
            )

        is DrawerUiState.Success -> {
            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Portrait -> PortraitDrawerScreen(
                    onNavigate = { },
                    onDrawNextCharacter = { drawerViewModel.drawNextCharacter() },
                    onFinishDraw = { drawerViewModel.finishDraw() },
                    state = state
                )

                is DeviceOrientation.Landscape -> LandscapeDrawerScreen(
                    onNavigate = { },
                    onDrawNextCharacter = { drawerViewModel.drawNextCharacter() },
                    onFinishDraw = { drawerViewModel.finishDraw() },
                    state = state
                )
            }
        }
    }
}


/**
 * PORTRAIT FUNCTIONS
 */

@Composable
fun PortraitDrawerScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            PortraitCompactScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )

        else ->
            PortraitMediumScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )
    }
}

@Composable
fun PortraitCompactScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}

@Composable
fun PortraitMediumScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}


/**
 * LANDSCAPE FUNCTIONS
 */

@Composable
fun LandscapeDrawerScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            LandscapeCompactScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )

        else ->
            LandscapeMediumScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )
    }
}

@Composable
fun LandscapeCompactScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}

@Composable
fun LandscapeMediumScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}