package com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component

import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppScreens

enum class BingoType(
    val bottomDestination: AppScreens,
    val bottomLabel: Int = R.string.drawer_screen,
    val bottomIsPremium: Boolean = false,
    val topDestination: AppScreens,
    val topLabel: Int = R.string.card_screen,
    val topIsPremium: Boolean = false,
    val avatar: Int,
    val background: Int,
    val enabled: Boolean = true
) {

    ONLINE(
        bottomDestination = AppScreens.CreateSession,
        bottomLabel = R.string.host,
        topDestination = AppScreens.Sessions,
        topLabel = R.string.join,
        avatar = R.drawable.smiling_monkey,
        background = R.drawable.blue_water
    ),

    THEMED(
        bottomDestination = AppScreens.Drawer,
        topDestination = AppScreens.Card,
        avatar = R.drawable.smiling_squirrel,
        background = R.drawable.green_water,
        bottomIsPremium = true
    ),

    CLASSIC(
        bottomDestination = AppScreens.ClassicDrawer,
        topDestination = AppScreens.ClassicCard,
        avatar = R.drawable.sphere,
        background = R.drawable.orange_water
    );


    companion object {
        fun getBingoTypes() = entries.toList()
    }
}