package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.landscape

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.DisplayOrderInfo
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.ThemesScreenLazyVerticalGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.ThemesScreenSearchBar
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.event.ThemesUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesScreenUiState

@Composable
fun ExpandedLandscapeThemesScreen(
    uiState: ThemesScreenUiState.Success,
    isSearching: Boolean,
    query: String,
    onEvent: (event: ThemesUiEvent) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .widthIn(max = 1200.dp)
        ) {

            DisplayOrderInfo(
                displayOrder = uiState.themesDisplayOrder,
                onDisplayOrderChange = { onEvent(ThemesUiEvent.OnDisplayOrderChange) },
                modifier = Modifier.padding(top = 12.dp)
            )

            ThemesScreenLazyVerticalGrid(
                themes = uiState.themes,
                columns = GridCells.Adaptive(minSize = 200.dp),
                contentSpacing = 12.dp,
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                onThemePick = { onEvent(ThemesUiEvent.OnThemePick(it)) }
            )

            ThemesScreenSearchBar(
                query = query,
                onQuery = { onEvent(ThemesUiEvent.OnQueryChange(it)) },
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