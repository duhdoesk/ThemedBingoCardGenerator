package com.duscaranari.themedbingocardsgenerator.domain.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun HomeScreen(
    navController: NavHostController,
    subscribed: Boolean
) {

    val context = LocalContext.current

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed,
                context = context
            )

        else ->
            LandscapeHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed,
                context = context
            )
    }
}