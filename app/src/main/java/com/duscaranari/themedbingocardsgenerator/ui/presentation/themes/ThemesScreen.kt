package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.getRawListOfThemes
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun ThemesScreen(
    themes: List<Theme>,
    onClick: (themeId: String) -> Unit
) {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitThemesScreen(
            themes = themes,
            onClick = { onClick(it) }
        )

        is DeviceOrientation.Landscape -> LandscapeThemesScreen(
            themes = themes,
            onClick = { onClick(it) }
        )
    }
}

@Composable
fun PortraitThemesScreen(
    themes: List<Theme>,
    onClick: (themeId: String) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .sizeIn(
                    maxWidth = 600.dp,
                    maxHeight = 1000.dp
                )
        ) {

            TopLabel(modifier = Modifier.padding(horizontal = 16.dp))

            ThemesLazyVerticalGrid(
                themes = themes,
                minSize = 140.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = { onClick(it) })

            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.squirrel_looking_up),
                    contentDescription = "Squirrel looking up",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
fun LandscapeThemesScreen(
    themes: List<Theme>,
    onClick: (themeId: String) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .sizeIn(
                    maxWidth = 1000.dp,
                    maxHeight = 600.dp
                )
        ) {

            Column(Modifier.weight(1f)) {

                TopLabel(modifier = Modifier.padding(horizontal = 16.dp))

                ThemesLazyVerticalGrid(
                    themes = themes,
                    minSize = 120.dp,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onClick = { onClick(it) })
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.smiling_tiger),
                    contentDescription = "Smiling Tiger",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }


    }
}


/**
 * COMPONENT
 */

@Composable
fun TopLabel(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {

        Text(
            text = stringResource(id = R.string.available_themes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ThemesLazyVerticalGrid(
    themes: List<Theme>,
    minSize: Dp,
    modifier: Modifier = Modifier,
    onClick: (themeId: String) -> Unit
) {

    Column(modifier = modifier) {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = minSize),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            for (theme in themes) {
                item {
                    ThemesCard(
                        theme = theme,
                        onClick = { onClick(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun ThemesCard(
    theme: Theme,
    onClick: (themeId: String) -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(theme.themeId) }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(theme.themePicture)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Theme Picture",
                placeholder = painterResource(id = R.drawable.compact_screen_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.1f)
            )

            Row(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                Text(
                    text = theme.themeName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        }
    }
}


/**
 * PREVIEWS
 */

@PortraitPreviews
@Composable
fun PortraitThemesPreview() {
    PortraitThemesScreen(themes = getRawListOfThemes(), onClick = { })
}

@LandscapePreviews
@Composable
fun LandscapeThemesPreview() {
    LandscapeThemesScreen(themes = getRawListOfThemes(), onClick = { })
}