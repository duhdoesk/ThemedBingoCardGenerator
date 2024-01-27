package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitThemesScreen(
    themes: List<Theme>,
    onClick: (themeId: String) -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {
        is WindowInfo.WindowType.Expanded ->
            ExpandedPortraitThemesScreen(
                themes = themes,
                onClick = { onClick(it) })

        is WindowInfo.WindowType.Medium ->
            ExpandedPortraitThemesScreen(
                themes = themes,
                onClick = { onClick(it) })

        else ->
            CompactPortraitThemesScreen(
                themes = themes,
                onClick = { onClick(it) })
    }
}