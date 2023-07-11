package com.duscaranari.themedbingocardsgenerator.presentation.themes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen

@Composable
fun ThemesScreen(
    navController: NavHostController,
    themesViewModel: ThemesViewModel = hiltViewModel()
) {

    when (val state = themesViewModel.themesState.collectAsState().value) {
        is ThemesState.Loading -> LoadingScreen()
        is ThemesState.Ready -> ThemesScreenReady(state)
    }
}

@Composable
fun ThemesScreenReady(state: ThemesState.Ready) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = "Ready!")
    }
}