package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize

@Composable
fun CardScreenGrid(
    characters: List<BingoCharacter>,
    cardSize: CardSize,
    modifier: Modifier = Modifier,
    spaceBetween: Dp = 4.dp
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(spaceBetween),
        modifier = modifier
            .animateContentSize()
    ) {

        val rows = cardSize.characterAmount / 3
        var index = 0

        for (row in 1..rows) {
            Row(horizontalArrangement = Arrangement.spacedBy(spaceBetween)) {
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