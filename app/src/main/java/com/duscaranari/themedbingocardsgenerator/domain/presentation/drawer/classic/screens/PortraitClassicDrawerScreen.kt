package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.ClassicDrawerUiState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.BingoSphere
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.DrawnSpheresLazyRow
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.themed.DrawerButtons
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitClassicDrawerScreen(
    uiState: ClassicDrawerUiState.Success
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Blue)
                .align(Alignment.TopCenter)
        ) {}

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .sizeIn(maxWidth = 600.dp, maxHeight = 1000.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Text(
                text = "Bingo Clássico",
                fontFamily = FontFamily.Cursive,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(32.dp))

            Text(text = "${uiState.drawnNumbers.size} de 75")

            Spacer(modifier = Modifier.height(12.dp))

            BingoSphere(
                sphereSize = 120.dp,
                canvasRadius = 108f,
                canvasStroke = 12f,
                fontSize = 48.sp,
                number = uiState.drawnNumbers.last().toString())

            Spacer(modifier = Modifier.height(12.dp))

            val numbers = uiState.drawnNumbers.dropLast(1).reversed()
            DrawnSpheresLazyRow(
                drawnNumbers = numbers,
                modifier = Modifier.padding(horizontal = 8.dp))

            Spacer(modifier = Modifier.height(32.dp))

            DrawerButtons(
                isFinished = uiState.isFinished,
                onDrawNextCharacter = { },
                onFinishDraw = { },
                onStartNewDraw = { })
        }
    }
}


/**
 * PORTRAIT PREVIEWS
 */
@PortraitPreviews
@Composable
fun PortraitPreview() {
    PortraitClassicDrawerScreen(
        uiState = ClassicDrawerUiState.Success(
            drawId = 1,
            isFinished = false,
            drawnNumbers = (1..75).toList().shuffled().subList(0, 12),
            availableNumbers = (1..75).toList().shuffled().subList(0, 12),
            numbers = (1..75).toList().shuffled().subList(0, 12)
        )
    )
}