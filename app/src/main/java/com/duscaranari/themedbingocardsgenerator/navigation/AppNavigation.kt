package com.duscaranari.themedbingocardsgenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.duscaranari.themedbingocardsgenerator.presentation.about.AboutScreen
import com.duscaranari.themedbingocardsgenerator.presentation.card.CardScreen
import com.duscaranari.themedbingocardsgenerator.presentation.home.HomeScreen
import com.duscaranari.themedbingocardsgenerator.presentation.themes.ThemesScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name) {

        composable(AppScreens.AboutScreen.name) {
            AboutScreen(navController)
        }

        composable(AppScreens.CardScreen.name) {
            CardScreen(navController)
        }

        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navController)
        }

        composable(AppScreens.ThemesScreen.name) {
            ThemesScreen(navController)
        }
    }
}