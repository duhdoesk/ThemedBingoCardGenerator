package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.getThemes
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.event.ThemesUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeThemesScreen(
    uiState: ThemesScreenUiState.Success,
    isSearching: Boolean,
    query: String,
    onEvent: (event: ThemesUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {

        is WindowInfo.WindowType.Compact ->
            RotateScreen()

        is WindowInfo.WindowType.Medium ->
            MediumLandscapeThemesScreen(
                uiState = uiState,
                onEvent = { onEvent(it) },
                isSearching = isSearching,
                query = query)

        else ->
            ExpandedLandscapeThemesScreen(
                uiState = uiState,
                onEvent = { onEvent(it) },
                isSearching = isSearching,
                query = query)
    }
}

@LandscapePreviews
@Composable
fun LandscapeThemesScreenPreview() {
    LandscapeThemesScreen(
        uiState = ThemesScreenUiState.Success(
            themes = getThemes(8),
            themesDisplayOrder = ThemesDisplayOrder.ID
        ),
        onEvent = {  },
        isSearching = false,
        query = "",
    )
}