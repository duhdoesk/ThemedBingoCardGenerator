package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.DisplayOrderInfo
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.ThemesScreenLazyVerticalGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.ThemesScreenSearchBar
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState

@Composable
fun ExpandedPortraitThemesScreen(
    uiState: ThemesScreenUiState.Success,
    onThemePick: (themeId: String) -> Unit,
    isSearching: Boolean,
    query: String,
    onQueryChange: (query: String) -> Unit,
    onDisplayOrderChange: () -> Unit
) {

    Box {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            DisplayOrderInfo(
                displayOrder = uiState.themesDisplayOrder,
                onDisplayOrderChange = { onDisplayOrderChange() }
            )

            ThemesScreenLazyVerticalGrid(
                themes = uiState.themes,
                columns = GridCells.Adaptive(minSize = 160.dp),
                contentSpacing = 4.dp,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f),
                onThemePick = { onThemePick(it) }
            )

            ThemesScreenSearchBar(
                query = query,
                onQuery = { onQueryChange(it) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (isSearching) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}