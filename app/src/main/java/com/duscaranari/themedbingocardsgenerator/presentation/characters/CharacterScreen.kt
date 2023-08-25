package com.duscaranari.themedbingocardsgenerator.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingImage
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun CharacterScreen(characterViewModel: CharacterViewModel = hiltViewModel()) {

    val charactersList = characterViewModel
        .charactersList
        .collectAsState()
        .value

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitCharacterScreen(charactersList)
        else -> LandscapeCharacterScreen(charactersList)
    }
}

@Composable
fun PortraitCharacterScreen(charactersList: List<Character>) {

    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = stringResource(id = R.string.available_characters),
            modifier = Modifier
                .padding(8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
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

@Composable
fun LandscapeCharacterScreen(charactersList: List<Character>) {

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

        LoadingImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.characterPicture)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = "Character Picture",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .weight(1f)
        )

        Text(
            text = "${ character.characterCardId }. ${ character.characterName}",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


/**
 * PREVIEW FUNCTIONS
 */

@PortraitPreviews
@Composable
fun PortraitCharacterScreenPreview() {
    PortraitCharacterScreen(charactersList = provideCharacterList())
}

//@LandscapePreviews
//@Composable
//fun LandscapeCharacterScreenPreview() {
//
//}

fun provideCharacterList(): List<Character> {

    val character = Character(
        characterId = "1",
        characterName = "Melancia Amarela",
        characterCardId = "1",
        characterThemeId = "1",
        characterPicture = "https://duhdoesk.github.io/bingo-bichinhos/img/ant.png"
    )

    return List(9) { character }
}