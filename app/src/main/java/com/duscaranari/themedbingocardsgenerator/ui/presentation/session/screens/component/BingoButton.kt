package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent

@Composable
fun BingoButton(
    card: List<BingoCharacter?>,
    drawnCharacters: List<String>,
    participantId: String?,
    winners: List<String>,
    event: (event: SessionUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    var buttonEnabled = true

    if (participantId in winners || card.size != 9)
        buttonEnabled = false
    else
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
        modifier = modifier
    ) {
        Text(
            text = "BINGO!",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}