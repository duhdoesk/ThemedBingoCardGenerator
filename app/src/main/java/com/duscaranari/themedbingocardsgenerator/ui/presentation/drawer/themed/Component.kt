package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.getImageLoader

@Composable
fun DrawerThemeText(
    text: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = "${stringResource(id = R.string.selected_theme)}: $text",
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
    )
}

@Composable
fun DrawerCounterText(
    text: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
    )
}

@Composable
fun DrawerDrawnText(
    modifier: Modifier = Modifier
) {

    Text(
        text = stringResource(id = R.string.drawn),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelSmall,
        modifier = modifier
    )
}

@Composable
fun DrawerCharacterImageAndName(
    character: BingoCharacter?,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        if (character == null) {
            Image(
                painter = painterResource(id = R.drawable.waving_octopus),
                contentDescription = "Character Picture",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .sizeIn(
                        maxWidth = 320.dp,
                        maxHeight = 320.dp
                    )
                    .aspectRatio(1f)
                    .padding(vertical = 2.dp)
            )

            Text(
                text = stringResource(id = R.string.start_drawing),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )
        } else {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.picture)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                placeholder = painterResource(id = R.drawable.compact_screen_logo),
                contentDescription = "Character Picture",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .sizeIn(
                        maxWidth = 320.dp,
                        maxHeight = 320.dp
                    )
                    .aspectRatio(1f)
                    .padding(vertical = 2.dp),
                imageLoader = getImageLoader(LocalContext.current)
            )

            Text(
                text = character.name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DrawerButtons(
    isFinished: Boolean,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit
) {

    when (isFinished) {
        true -> {
            Button(
                onClick = onStartNewDraw,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text(text = stringResource(id = R.string.start_new_draw))
            }
        }

        false -> {
            Button(
                onClick = onDrawNextCharacter,
                modifier = Modifier.width(160.dp)
            ) {
                Text(text = stringResource(id = R.string.draw_next_character))
            }

            TextButton(
                onClick = onFinishDraw,
                modifier = Modifier.width(160.dp)
            ) {
                Text(text = stringResource(id = R.string.finish_draw))
            }
        }
    }
}

@Composable
fun DrawerLazyGrid(
    characters: List<BingoCharacter>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier.clickable { onClick() }
    ) {

        for (c in characters) {
            item {
                Card {
                    Text(
                        text = c.name.uppercase(),
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}
