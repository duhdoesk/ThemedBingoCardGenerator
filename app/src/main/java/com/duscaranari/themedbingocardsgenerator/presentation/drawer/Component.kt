package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.presentation.component.getImageLoader

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
    character: Character,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.characterPicture)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            placeholder = painterResource(id = R.drawable.compact_screen_logo),
            contentDescription = "Character Picture",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .sizeIn(
                    minWidth = 240.dp,
                    maxWidth = 320.dp,
                    minHeight = 240.dp,
                    maxHeight = 280.dp
                ),
            imageLoader = getImageLoader(LocalContext.current)
        )

        Text(
            text = character.characterName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth()
        )
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
                modifier = Modifier.width(200.dp),
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
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.draw_next_character))
            }

            TextButton(
                onClick = onFinishDraw,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.finish_draw))
            }
        }
    }
}
