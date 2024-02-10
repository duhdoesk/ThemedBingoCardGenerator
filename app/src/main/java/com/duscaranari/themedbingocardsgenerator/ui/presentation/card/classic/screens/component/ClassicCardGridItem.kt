package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kotlin.random.Random

@Composable
fun ClassicCardGridItem(number: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(
                color = Color(
                    Random.nextInt(220, 245),
                    Random.nextInt(220, 245),
                    Random.nextInt(220, 245)
                )
            )
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black,
            color = Color.Black
        )
    }
}