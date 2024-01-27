@file:OptIn(ExperimentalMaterial3Api::class)

package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.Character
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingImage
import kotlin.random.Random

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
fun CardScreenGrid(
    characters: List<Character>,
    cardSize: CardSize,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        val rows = cardSize.characterAmount / 3
        var index = 0

        for (row in 1..rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                CardScreenCards(
                    characters[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.9f)
                        .weight(1f)
                )

                CardScreenCards(
                    characters[index + 1],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.9f)
                        .weight(1f)
                )

                CardScreenCards(
                    characters[index + 2],
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.9f)
                        .weight(1f)
                )

                index += 3
            }
        }
    }
}

@Composable
fun CardScreenCards(
    character: Character,
    modifier: Modifier = Modifier
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        border = CardDefaults.outlinedCardBorder(),
        modifier = modifier
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

                Box(contentAlignment = Alignment.Center) {

                    Surface(
                        color = Color(
                            blue = Random.nextInt(160, 256),
                            red = Random.nextInt(160, 256),
                            green = Random.nextInt(160, 256),
                            alpha = 255
                        ),
                        modifier = Modifier.fillMaxSize()
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
                        )
                    }
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
                    overflow = TextOverflow.Ellipsis,
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

@Composable
fun SizeSelectorSwitchButton(
    optionSelected: CardSize,
    onClick: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(optionSelected == CardSize.LARGE) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.card_size))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.medium_card)
            )

            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = !checkedState.value
                    onClick(checkedState.value)
                })

            Text(
                text = stringResource(id = R.string.large_card)
            )
        }
    }
}