package com.duscaranari.themedbingocardsgenerator.presentation.card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun CardScreen(
    navController: NavHostController,
    cardViewModel: CardViewModel = hiltViewModel(),
    themeId: String
) {
    Text(text = "Card Screen $themeId")
}