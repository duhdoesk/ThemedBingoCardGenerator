package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component

import androidx.compose.material3.ColorScheme
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.ThemedBingoLightColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getThemedBingoColorScheme

enum class BingoType(
    val drawerDestination: AppScreens,
    val cardDestination: AppScreens,
    val avatar: Int,
    val background: Int
) {

    THEMED(
        drawerDestination = AppScreens.Drawer,
        cardDestination = AppScreens.Card,
        avatar = R.drawable.smiling_squirrel,
        background = R.drawable.green_water
    ),

    CLASSIC(
        drawerDestination = AppScreens.ClassicDrawer,
        cardDestination = AppScreens.ClassicCard,
        avatar = R.drawable.sphere,
        background = R.drawable.orange_water
    ),

    ONLINE(
        drawerDestination = AppScreens.ClassicDrawer,
        cardDestination = AppScreens.ClassicCard,
        avatar = R.drawable.smiling_monkey,
        background = R.drawable.blue_water
    );


    companion object {
        fun getBingoTypes() = entries.toList()
    }
}