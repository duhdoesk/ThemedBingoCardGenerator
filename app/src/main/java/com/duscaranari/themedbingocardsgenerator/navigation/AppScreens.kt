package com.duscaranari.themedbingocardsgenerator.navigation

import com.duscaranari.themedbingocardsgenerator.R

enum class AppScreens (val stringResource: Int) {

    About(
        stringResource = R.string.about_screen),

    Card(
        stringResource = R.string.card_screen),

    Home(
        stringResource = R.string.home_screen),

    Themes(
        stringResource = R.string.themes_screen),

    Character(
        stringResource = R.string.character_screen),

    Drawer(
        stringResource = R.string.drawer_screen),

    Subs(
        stringResource = R.string.subs_screen),

    ClassicDrawer(
        stringResource = R.string.classic_drawer_screen)
}