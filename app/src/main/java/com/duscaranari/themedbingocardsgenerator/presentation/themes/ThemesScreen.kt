package com.duscaranari.themedbingocardsgenerator.presentation.themes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ThemesScreen(
    navController: NavHostController,
    themesViewModel: ThemesViewModel = hiltViewModel()
) {
    Text(text = "Themes Screen")
}