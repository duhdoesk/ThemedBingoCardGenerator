package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun DrawerScreen(
    navController: NavHostController,
    drawerViewModel: DrawerViewModel = hiltViewModel()
) {

    when (val state = drawerViewModel.uiState.collectAsState().value) {

        is DrawerUiState.Loading -> LoadingScreen()
        is DrawerUiState.Error -> ErrorScreen(state.errorMessage)
        is DrawerUiState.Success -> {

            when (rememberDeviceOrientation()) {

                is DeviceOrientation.Portrait -> PortraitDrawerScreen()
                is DeviceOrientation.Landscape -> LandscapeDrawerScreen()
            }
        }
    }
}

@Composable
fun PortraitDrawerScreen() {

}

@Composable
fun LandscapeDrawerScreen() {

}