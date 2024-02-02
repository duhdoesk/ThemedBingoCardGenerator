package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.getThemes
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitThemesScreen(
    themes: List<Theme>,
    onThemePick: (themeId: String) -> Unit,
    isSearching: Boolean,
    query: String,
    onQueryChange: (query: String) -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            CompactPortraitThemesScreen(
                themes = themes,
                onThemePick = { onThemePick(it) },
                onQueryChange = { onQueryChange(it) },
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
        themes = getThemes(),
        onThemePick = {  },
        isSearching = false,
        query = "",
        onQueryChange = {  }
    )
}