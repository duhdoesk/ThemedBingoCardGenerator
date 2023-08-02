@file:OptIn(ExperimentalMaterial3Api::class)

package com.duscaranari.themedbingocardsgenerator.presentation.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.model.Character
import com.duscaranari.themedbingocardsgenerator.model.Theme
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingImage
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.DevicePreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun CardScreen(
    cardViewModel: CardViewModel = hiltViewModel()
) {

    when (val state = cardViewModel.cardState.collectAsState().value) {
        is CardState.Loading ->
            LoadingScreen()

        is CardState.Ready -> {

            when (rememberDeviceOrientation()) {

                is DeviceOrientation.Landscape ->
                    LandscapeCardScreen(state, cardViewModel)

                else ->
                    PortraitCardScreen(state, cardViewModel)
            }
        }
    }
}

@Composable
fun PortraitCardScreen(state: CardState.Ready, cardViewModel: CardViewModel) {

    when (rememberWindowInfo().screenHeightInfo) {

        is WindowInfo.WindowType.Compact ->
            CardPortraitCompactScreen(
                state,
                cardViewModel
            )

        else ->
            CardPortraitExpandedScreen(
                state,
                cardViewModel
            )
    }
}

@Composable
fun LandscapeCardScreen(state: CardState.Ready, cardViewModel: CardViewModel) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            CardPortraitCompactScreen(
                state,
                cardViewModel
            )

        else ->
            CardPortraitExpandedScreen(
                state,
                cardViewModel
            )
    }
}


/*
SCREENS
 */

@Composable
fun CardPortraitCompactScreen(
    state: CardState.Ready,
    cardViewModel: CardViewModel
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CardScreenHeader(
            theme = state.currentTheme,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 16.dp)
        )

        Spacer(Modifier.height(16.dp))

        CardScreenLazyVerticalGrid(
            characters = state.drawnCharacters,
            columns = 3,
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )

        Spacer(Modifier.height(16.dp))

        CardScreenName(
            onChange = { user -> cardViewModel.updateCurrentUser(user) },
            currentUser = state.currentUser,
            modifier = Modifier
                .padding(horizontal = 32.dp)
        )

        Spacer(Modifier.weight(1f))

        NewCardButton(
            onClick = { cardViewModel.drawNewCard() },
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun CardPortraitExpandedScreen(
    state: CardState.Ready,
    cardViewModel: CardViewModel
) {

    Row(modifier = Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {

            CardScreenHeader(theme = state.currentTheme)

            Spacer(Modifier.height(24.dp))

            CardScreenName(
                onChange = { user -> cardViewModel.updateCurrentUser(user) },
                currentUser = state.currentUser
            )

            Spacer(Modifier.height(24.dp))

            NewCardButton(
                onClick = { cardViewModel.drawNewCard() }
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(0.9f)
        ) {

            CardScreenLazyVerticalGrid(
                characters = state.drawnCharacters,
                columns = 3,
                modifier = Modifier
                    .padding(32.dp)
            )
        }
    }
}

@Composable
fun CardLandscapeCompactScreen(
    state: CardState.Ready,
    cardViewModel: CardViewModel
) {

}

@Composable
fun CardLandscapeExpandedScreen(
    state: CardState.Ready,
    cardViewModel: CardViewModel
) {

}


/*
COMMON COMPOSABLE FUNCTIONS
 */

@Composable
fun CardScreenHeader(
    theme: Theme,
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    textAlignment: TextAlign = TextAlign.Center
) {

    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = modifier,
    ) {

        Text(
            text = stringResource(id = R.string.selected_theme),
            style = MaterialTheme.typography.labelMedium,
            textAlign = textAlignment
        )

        Text(
            text = theme.themeName,
            style = MaterialTheme.typography.titleLarge,
            textAlign = textAlignment
        )
    }
}

@Composable
fun CardScreenLazyVerticalGrid(
    characters: List<Character>,
    columns: Int,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            for (character in characters) {
                item {
                    CardScreenCards(character)
                }
            }
        }
    }
}

@Composable
fun CardScreenCards(character: Character) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(character.characterPicture)
//                        .crossfade(true)
//                        .scale(Scale.FILL)
//                        .build(),
//                    contentScale = ContentScale.Fit,
//                    contentDescription = "Theme Picture",
//                    placeholder = painterResource(id = R.drawable.compact_screen_logo),
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                        .aspectRatio(1.1f)
//                )

                LoadingImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.characterPicture)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = "Character Picture",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .aspectRatio(1.1f)
                        .padding(16.dp)
                ) {

                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.BottomEnd)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.hexagon),
                        contentDescription = "Hexagon",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(36.dp)
                    )

                    Text(
                        text = character.characterCardId,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
            ) {

                Text(
                    text = character.characterName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun CardScreenName(
    modifier: Modifier = Modifier,
    onChange: (newUser: String) -> Unit,
    currentUser: String
) {

    val showDialog = remember { mutableStateOf(false) }

    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = modifier
            .clickable { showDialog.value = true }
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(12.dp)
        ) {

            Text(
                text = "Nome:",
                style = MaterialTheme.typography.labelLarge
            )

            Text(
                text = currentUser,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        if (showDialog.value) {
            NameDialog(
                currentUser = currentUser,
                onChange = { onChange(it) },
                onDismiss = { showDialog.value = false })
        }
    }
}

@Composable
fun NewCardButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier,
        onClick = { onClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Plus Sign"
            )

            Text(
                text = stringResource(id = R.string.new_card),
                modifier = Modifier
                    .padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun NameDialog(
    currentUser: String,
    onChange: (newUsername: String) -> Unit,
    onDismiss: (boolean: Boolean) -> Unit
) {

    Dialog(
        onDismissRequest = { onDismiss(false) }
    ) {

        var newUser by remember { mutableStateOf(currentUser) }

        Card {

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            ) {

                TextField(
                    value = newUser,

                    onValueChange = {
                        if (it.length <= 20) {
                            newUser = it
                        }
                    },

                    label = { Text(text = stringResource(id = R.string.insert_your_name)) },

                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),

                    keyboardActions = KeyboardActions(
                        onDone = {
                            onChange(newUser)
                            onDismiss(false)
                        })
                )

                TextButton(onClick = {
                    onChange(newUser)
                    onDismiss(false)
                }) {

                    Text("OK")
                }
            }
        }
    }
}


// PREVIEWS

@DevicePreviews
@Composable
fun CardPreview() {

    CardScreenCards(
        character = Character(
            characterCardId = "1",
            characterName = "Test",
            characterPicture = "Test",
            characterId = "1",
            characterThemeId = "1"
        )
    )
}