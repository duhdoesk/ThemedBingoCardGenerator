package com.duscaranari.themedbingocardsgenerator.ui.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.landscape.LandscapeHomeScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.portrait.PortraitHomeScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun HomeScreen(
    navController: NavHostController,
    subscribed: Boolean,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    val bingoTypes = BingoType.getBingoTypes()

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed,
                bingoTypes = bingoTypes,
                onBingoTypeChange = { onBingoTypeChange(it) }
            )

        else ->
            LandscapeHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed,
                bingoTypes = bingoTypes,
                onBingoTypeChange = { onBingoTypeChange(it) }
            )
    }
}