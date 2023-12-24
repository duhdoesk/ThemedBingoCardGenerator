package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawnSpheresLazyRow(
    modifier: Modifier = Modifier,
    drawnNumbers: List<Int>
) {
    LazyRow(
        modifier = modifier
    ) {

        for (number in drawnNumbers) {
            item {
                BingoSphere(
                    sphereSize = 48.dp,
                    canvasRadius = 43.2f,
                    canvasStroke = 4.8f,
                    fontSize = 19.sp,
                    number = number.toString()
                )
            }
        }
    }
}