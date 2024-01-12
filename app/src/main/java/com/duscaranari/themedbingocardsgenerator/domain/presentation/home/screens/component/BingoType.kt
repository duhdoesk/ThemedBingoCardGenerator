package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component

import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens

enum class BingoType(
    name: Int,
    val drawerDestination: AppScreens,
    val cardDestination: AppScreens,
    val picture: Int
) {

    THEMED(
        name = R.string.themed,
        drawerDestination = AppScreens.Drawer,
        cardDestination = AppScreens.Card,
        picture = R.drawable.smiling_squirrel
    ),

    CLASSIC(
        name = R.string.classic,
        drawerDestination = AppScreens.ClassicDrawer,
        cardDestination = AppScreens.ClassicCard,
        picture = R.drawable.sphere
    ),

    ONLINE(
        name = R.string.online,
        drawerDestination = AppScreens.ClassicDrawer,
        cardDestination = AppScreens.ClassicCard,
        picture = R.drawable.smiling_monkey
    )
}