package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.getThemes
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitThemesScreen(
    uiState: ThemesScreenUiState.Success,
    onThemePick: (themeId: String) -> Unit,
    isSearching: Boolean,
    query: String,
    onQueryChange: (query: String) -> Unit,
    onDisplayOrderChange: () -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            CompactPortraitThemesScreen(
                uiState = uiState,
                onThemePick = { onThemePick(it) },
                onQueryChange = { onQueryChange(it) },
                onDisplayOrderChange = { onDisplayOrderChange() },
                isSearching = isSearching,
                query = query)

        else ->
            ExpandedPortraitThemesScreen(
                onThemePick = { onThemePick(it) },
                onQueryChange = { onQueryChange(it) },
                isSearching = isSearching,
                query = query)
    }
}

@PortraitPreviews
@Composable
fun PortraitThemesScreenPreview() {
    PortraitThemesScreen(
        uiState = ThemesScreenUiState.Success(
            themes = getThemes(),
            themesDisplayOrder = ThemesDisplayOrder.ID
        ),
        onThemePick = {  },
        isSearching = false,
        query = "",
        onQueryChange = {  },
        onDisplayOrderChange = {  }
    )
}