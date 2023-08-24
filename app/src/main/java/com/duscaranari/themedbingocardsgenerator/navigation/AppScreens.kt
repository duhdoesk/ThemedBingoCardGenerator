package com.duscaranari.themedbingocardsgenerator.navigation

import androidx.compose.ui.res.stringResource
import com.duscaranari.themedbingocardsgenerator.R

enum class AppScreens (val stringResource: Int) {

    About(
        stringResource = R.string.card_screen),

    Card(
        stringResource = R.string.card_screen),

    Home(
        stringResource = R.string.home_screen),

    Themes(
        stringResource = R.string.themes_screen),

    Character(
        stringResource = R.string.character_screen)
}