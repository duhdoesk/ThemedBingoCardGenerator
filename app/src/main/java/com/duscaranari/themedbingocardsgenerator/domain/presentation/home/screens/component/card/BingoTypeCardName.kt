package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType

@Composable
fun BingoTypeCardName(
    colorScheme: ColorScheme,
    bingoType: BingoType
) {
    Box {
        Text(
            text = bingoType.name.lowercase(),
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            color = colorScheme.primary
        )

        Text(
            text = bingoType.name.lowercase(),
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold,
            color = colorScheme.onPrimary,
            modifier = Modifier.offset((-2).dp)
        )
    }
}