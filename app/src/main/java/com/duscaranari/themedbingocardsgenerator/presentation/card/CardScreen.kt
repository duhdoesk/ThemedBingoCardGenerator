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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.model.Character
import com.duscaranari.themedbingocardsgenerator.model.Theme
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun CardScreen(
    cardViewModel: CardViewModel = hiltViewModel()
) {

    when (val state = cardViewModel.cardState.collectAsState().value) {
        is CardState.Loading ->
            LoadingScreen()

        is CardState.Ready -> {

            val windowInfo = rememberWindowInfo()

            when (windowInfo.screenWidthInfo) {

                is WindowInfo.WindowType.Compact -> CardCompactScreen(
                    state,
                    cardViewModel
                )

                else -> CardMediumScreen(state)
            }
        }
    }
}


/*
SCREENS
 */

@Composable
fun CardCompactScreen(
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
fun CardMediumScreen(state: CardState.Ready) {

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
            text = "Tema Escolhido:",
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
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Box(contentAlignment = Alignment.BottomEnd) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.characterPicture)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentScale = ContentScale.Fit,
                    contentDescription = "Theme Picture",
                    placeholder = painterResource(id = R.drawable.compact_screen_logo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.1f)
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.padding(4.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.hexagon),
                        contentDescription = "Hexagon",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                        modifier = Modifier.size(40.dp)
                    )

                    Text(
                        text = character.characterId,
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {

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
                text = "Nova Cartela",
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

                    label = { Text(text = "Insira seu nome") },

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