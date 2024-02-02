package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait.PortraitThemesScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun ThemesScreen(
    onThemePick: (themeId: String) -> Unit,
    themesViewModel: ThemesViewModel = hiltViewModel()
) {

    val query by themesViewModel.query.collectAsState()
    val isSearching by themesViewModel.isSearching.collectAsState()

    when (val uiState = themesViewModel.uiState.collectAsState().value) {
        is ThemesScreenUiState.Success -> {
            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Portrait -> PortraitThemesScreen(
                    themes = uiState.themes,
                    onThemePick = { onThemePick(it) },
                    onQueryChange = themesViewModel::onQueryChange,
                    isSearching = isSearching,
                    query = query
                )

                is DeviceOrientation.Landscape -> {  }
            }
        }

        else -> LoadingScreen()
    }
}