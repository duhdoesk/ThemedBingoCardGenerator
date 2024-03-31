package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent

@Composable
fun CharactersChipsGrid(
    drawnCharacters: List<String>,
    card: List<BingoCharacter?>,
    event: (event: SessionUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(card) { character ->
                character?.let { characterNotNull ->
                    val cardColors =
                        if (drawnCharacters.contains(characterNotNull.id))
                            CardDefaults.cardColors()
                                .copy(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                        else
                            CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                contentColor = MaterialTheme.colorScheme.onBackground
                            )

                    Card(
                        colors = cardColors,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = characterNotNull.name,
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        var buttonEnabled = true
        card.forEach { character ->
            character?.let { characterNotNull ->
                if (!drawnCharacters.contains(characterNotNull.id))
                    buttonEnabled = false
                return@forEach
            }
        }

        Button(
            onClick = { event(SessionUiEvent.OnAddWinner) },
            enabled = buttonEnabled,
            modifier = Modifier.widthIn(320.dp)
        ) {
            Text(text = "BINGO!")
        }
    }
}