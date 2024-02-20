package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.getThemes
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.event.ThemesUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitThemesScreen(
    uiState: ThemesScreenUiState.Success,
    isSearching: Boolean,
    query: String,
    onEvent: (event: ThemesUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            CompactPortraitThemesScreen(
                uiState = uiState,
                onEvent = { onEvent(it) },
                isSearching = isSearching,
                query = query)

        else ->
            ExpandedPortraitThemesScreen(
                uiState = uiState,
                onEvent = { onEvent(it) },
                isSearching = isSearching,
                query = query)
    }
}

@PortraitPreviews
@Composable
fun PortraitThemesScreenPreview() {
    PortraitThemesScreen(
        uiState = ThemesScreenUiState.Success(
            themes = getThemes(9),
            themesDisplayOrder = ThemesDisplayOrder.ID
        ),
        onEvent = {  },
        isSearching = true,
        query = "",
    )
}