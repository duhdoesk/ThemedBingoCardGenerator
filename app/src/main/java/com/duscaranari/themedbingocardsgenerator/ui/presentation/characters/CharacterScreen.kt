package com.duscaranari.themedbingocardsgenerator.ui.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.getImageLoader
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun CharacterScreen(characterViewModel: CharacterViewModel = hiltViewModel()) {

    val charactersList = characterViewModel
        .charactersList
        .collectAsStateWithLifecycle()
        .value

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitCharacterScreen(charactersList)
        else -> LandscapeCharacterScreen(charactersList)
    }
}

@Composable
fun PortraitCharacterScreen(charactersList: List<Character>) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.sizeIn(maxWidth = 600.dp, maxHeight = 1000.dp)) {

            Text(
                text = stringResource(id = R.string.available_characters),
                modifier = Modifier
                    .padding(8.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {

                for (character in charactersList) {
                    item {
                        CharacterScreenCard(
                            character = character,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LandscapeCharacterScreen(charactersList: List<Character>) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.sizeIn(maxWidth = 1000.dp, maxHeight = 600.dp)) {

            Text(
                text = stringResource(id = R.string.available_characters),
                modifier = Modifier
                    .padding(8.dp)
            )

            LazyHorizontalGrid(
                rows = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {

                for (character in charactersList) {
                    item {
                        CharacterScreenCard(
                            character = character,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}


/**
 * COMMON COMPOSABLE FUNCTIONS
 */

@Composable
fun CharacterScreenCard(
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
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .aspectRatio(1f),
            imageLoader = getImageLoader(LocalContext.current)
        )

        Text(
            text = character.characterName,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


/**
 * PREVIEW FUNCTIONS
 */

@PortraitPreviews
@LandscapePreviews
@Composable
fun CharacterScreenPreview() {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitCharacterScreen(charactersList = provideCharacterList())

        else ->
            LandscapeCharacterScreen(charactersList = provideCharacterList())
    }
}

fun provideCharacterList(): List<Character> {

    val character = Character(
        characterId = "1",
        characterName = "Melancia Azul",
        characterCardId = "1",
        characterThemeId = "1",
        characterPicture = "https://duhdoesk.github.io/bingo-bichinhos/img/ant.png"
    )

    return List(9) { character }
}