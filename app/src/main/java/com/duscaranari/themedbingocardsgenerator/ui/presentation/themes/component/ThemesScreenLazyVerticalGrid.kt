package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme

@Composable
fun ThemesScreenLazyVerticalGrid(
    themes: List<BingoTheme>,
    modifier: Modifier = Modifier,
    columns: GridCells,
    onThemePick: (themeId: String) -> Unit,
    contentSpacing: Dp
) {

    LazyVerticalGrid(
        columns = columns,
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(contentSpacing),
        verticalArrangement = Arrangement.spacedBy(contentSpacing)
    ) {
        for (theme in themes) {
            item {
                ThemesScreenCard(
                    theme = theme,
                    modifier = Modifier
                        .aspectRatio(1f),
                    onThemePick = { onThemePick(theme.id) }
                )
            }
        }
    }
}

@Preview
@Composable
fun ThemesScreenLazyVerticalGridPreview() {
    ThemesScreenLazyVerticalGrid(
        themes = getThemes(),
            columns = GridCells.Adaptive(minSize = 240.dp),
            modifier = Modifier.width(480.dp),
        onThemePick = {  },
        contentSpacing = 8.dp
        )
}

fun getThemes(amount: Int = 4): List<BingoTheme> {
    return List(amount) {
        BingoTheme(
            name = "Halloween",
            picture = "https://i.imgur.com/emkvM5I.jpg",
            characters = emptyList()
        )
    }
}