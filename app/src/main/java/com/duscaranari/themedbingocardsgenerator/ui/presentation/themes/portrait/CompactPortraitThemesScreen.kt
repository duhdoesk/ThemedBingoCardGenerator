package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.portrait

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.ThemesScreenLazyVerticalGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component.ThemesScreenSearchBar

@Composable
fun CompactPortraitThemesScreen(
    themes: List<Theme>,
    onThemePick: (themeId: String) -> Unit,
    isSearching: Boolean,
    query: String,
    onQueryChange: (query: String) -> Unit
) {

    Box(
        modifier = Modifier
            .imePadding()
            .fillMaxSize()
    ) {
        Column {

            ThemesScreenSearchBar(
                query = query,
                onQuery = { onQueryChange(it) },
                modifier = Modifier.fillMaxWidth()
            )

            ThemesScreenLazyVerticalGrid(
                themes = themes,
                columns = GridCells.Fixed(2),
                contentSpacing = 12.dp,
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
                ,
                onThemePick = { onThemePick(it) })
        }

        if (isSearching) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}