package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun BingoTypeCardSoonMark(
    modifier: Modifier = Modifier,
    colorScheme: ColorScheme
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        ) {

            drawRect(
                color = colorScheme.primary
            )
        }

        Text(
            text = stringResource(id = R.string.soon),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black,
            color = colorScheme.primary,
            modifier = Modifier
                .padding(end = 16.dp)
        )

        Text(
            text = stringResource(id = R.string.soon),
            style = MaterialTheme.typography.titleLarge,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Black,
            color = colorScheme.onPrimary,
            modifier = Modifier
                .padding(end = 16.dp)
                .offset((-2).dp)
        )
    }
}