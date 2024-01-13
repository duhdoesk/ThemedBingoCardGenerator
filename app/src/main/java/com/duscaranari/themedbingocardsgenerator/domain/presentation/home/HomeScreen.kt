package com.duscaranari.themedbingocardsgenerator.domain.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.LandscapeHomeScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.PortraitHomeScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun HomeScreen(
    navController: NavHostController,
    subscribed: Boolean,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    val context = LocalContext.current

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed,
                context = context,
                onBingoTypeChange = { onBingoTypeChange(it) }
            )

        else ->
            LandscapeHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed,
                context = context,
                onBingoTypeChange = { onBingoTypeChange(it) }
            )
    }
}