@file:OptIn(ExperimentalMaterial3Api::class)

package com.duscaranari.themedbingocardsgenerator.presentation.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
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
import androidx.compose.ui.unit.Dp
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
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun CardScreen(cardViewModel: CardViewModel = hiltViewModel()) {

    when (val state = cardViewModel.cardState.collectAsState().value) {
        is CardState.Loading ->
            LoadingScreen()

        is CardState.Ready -> {

            when (rememberDeviceOrientation()) {

                is DeviceOrientation.Landscape ->
                    CardLandscapeScreen(
                        state = state,
                        onUpdateCurrentUser = { cardViewModel.updateCurrentUser(it) },
                        onDrawNewCard = { cardViewModel.drawNewCard() }
                    )

                else ->
                    CardPortraitScreen(
                        state = state,
                        onUpdateCurrentUser = { cardViewModel.updateCurrentUser(it) },
                        onDrawNewCard = { cardViewModel.drawNewCard() }
                    )
            }
        }
    }
}

@Composable
fun CardPortraitScreen(
    state: CardState.Ready,
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit
) {

    CardPortraitUniversalScreen(
        onUpdateCurrentUser = { onUpdateCurrentUser(it) },
        onDrawNewCard = { onDrawNewCard() },
        state = state
    )
}

@Composable
fun CardLandscapeScreen(
    state: CardState.Ready,
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {

        is WindowInfo.WindowType.Compact ->
            CardLandscapeCompactScreen(
                state = state,
                onUpdateCurrentUser = { onUpdateCurrentUser(it) },
                onDrawNewCard = onDrawNewCard
            )

        else ->
            CardLandscapeExpandedScreen(
                state = state,
                onUpdateCurrentUser = { onUpdateCurrentUser(it) },
                onDrawNewCard = onDrawNewCard
            )
    }
}


/*
SCREENS
 */

@Composable
fun CardPortraitUniversalScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardState.Ready
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        CardScreenHeader(theme = state.currentTheme)

        CardScreenLazyVerticalGrid(
            characters = state.drawnCharacters,
            columns = 3,
            modifier = Modifier
                .widthIn(max = 600.dp)
        )

        CardScreenName(
            onChange = { onUpdateCurrentUser(it) },
            currentUser = state.currentUser,
        )

        NewCardButton(
            onClick = { onDrawNewCard() }
        )
    }
}

@Composable
fun CardLandscapeCompactScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardState.Ready
) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CardScreenLazyHorizontalGrid(
                characters = state.drawnCharacters,
                rows = 3,
                spacing = 4.dp,
                modifier = Modifier
                    .fillMaxHeight(),
                cardModifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1.2f)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxHeight()
        ) {

            CardScreenHeader(theme = state.currentTheme)

            CardScreenName(
                onChange = { onUpdateCurrentUser(it) },
                currentUser = state.currentUser
            )

            NewCardButton(
                onClick = { onDrawNewCard() }
            )
        }
    }
}

@Composable
fun CardLandscapeExpandedScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardState.Ready
) {

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {

            CardScreenLazyHorizontalGrid(
                characters = state.drawnCharacters,
                rows = 3,
                spacing = 8.dp,
                modifier = Modifier
                    .padding(16.dp)
                    .heightIn(max = 600.dp),
                cardModifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f),
            )

            CardScreenName(
                onChange = { onUpdateCurrentUser(it) },
                currentUser = state.currentUser
            )

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
        ) {

            CardScreenHeader(theme = state.currentTheme)

            NewCardButton(
                onClick = { onDrawNewCard() }
            )
        }
    }
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
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {

            for (character in characters) {
                item {
                    CardScreenCards(
                        character,
                        cardModifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.9f)
                    )
                }
            }
        }
    }
}

@Composable
fun CardScreenLazyHorizontalGrid(
    characters: List<Character>,
    rows: Int,
    modifier: Modifier = Modifier,
    cardModifier: Modifier = Modifier,
    spacing: Dp
) {

    Column(modifier = modifier) {

        LazyHorizontalGrid(
            rows = GridCells.Fixed(rows),
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalArrangement = Arrangement.spacedBy(spacing),
            contentPadding = PaddingValues(spacing)
        ) {

            for (character in characters) {
                item {
                    CardScreenCards(
                        character = character,
                        cardModifier = cardModifier
                    )
                }
            }
        }
    }
}

@Composable
fun CardScreenCards(
    character: Character,
    cardModifier: Modifier = Modifier
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = CardDefaults.outlinedCardBorder(),
        modifier = cardModifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

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
                        .padding(8.dp)
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

@PortraitPreviews
@Composable
fun PortraitPreview() {

    val characters = getRawListOfCharacters()

    CardPortraitScreen(
        onUpdateCurrentUser = { },
        onDrawNewCard = { },
        state = CardState.Ready(
            currentTheme = Theme(
                themeId = "1",
                themeName = "Bears",
                themePicture = ""
            ),
            currentUser = "Dwight Schrute",
            drawnCharacters = characters
        )
    )
}

@LandscapePreviews
@Composable
fun LandscapePreview() {

    val characters = getRawListOfCharacters()

    CardLandscapeScreen(
        onUpdateCurrentUser = { },
        onDrawNewCard = { },
        state = CardState.Ready(
            currentTheme = Theme(
                themeId = "1",
                themeName = "Bears",
                themePicture = ""
            ),
            currentUser = "Dwight Schrute",
            drawnCharacters = characters
        )
    )

}

fun getRawListOfCharacters(): List<Character> {

    val character = Character(
        characterThemeId = "1",
        characterId = "1",
        characterPicture = "",
        characterName = "Character",
        characterCardId = "1"
    )

    return List(9) { character }
}