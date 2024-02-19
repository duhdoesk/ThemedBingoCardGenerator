package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.getImageLoader

@Composable
fun ThemesScreenCard(
    theme: Theme,
    modifier: Modifier = Modifier,
    onThemePick: () -> Unit
) {

    Card(
        modifier = modifier
            .clickable { onThemePick() }
    ) {

        Box {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(theme.themePicture)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                placeholder = painterResource(id = R.drawable.compact_screen_logo),
                contentDescription = "Character Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                imageLoader = getImageLoader(LocalContext.current)
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.primary)
            ) {

                Text(
                    text = theme.themeName,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun ThemesScreenCardPreview() {
    ThemesScreenCard(
        theme = Theme(
            themeId = "1",
            themeName = "Halloween",
            themePicture = "https://i.imgur.com/emkvM5I.jpg"
        ),
        modifier = Modifier
            .aspectRatio(2f)
            .width(400.dp),
        onThemePick = {  }
    )
}