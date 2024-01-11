package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.ClassicDrawerUiState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.BingoSphere
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.ClassicBingoHeadline
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.DrawingCounter
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.DrawnSpheresLazyGrid
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.DrawnText
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.themed.DrawerButtons
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitClassicDrawerScreen(
    uiState: ClassicDrawerUiState.Success,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit,
    onCopyDrawn: () -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .sizeIn(maxWidth = 600.dp, maxHeight = 1000.dp)
                .verticalScroll(rememberScrollState())
        ) {

            ClassicBingoHeadline()

            Spacer(modifier = Modifier.height(8.dp))

            DrawingCounter(
                total = uiState.numbers.size.toString(),
                drawn = uiState.drawnNumbers.size.toString()
            )

            if (uiState.drawnNumbers.isEmpty()) {
                Image(
                    painter = painterResource(id = R.drawable.waving_octopus),
                    contentDescription = stringResource(id = R.string.waving_octopus),
                    modifier = Modifier
                        .size(120.dp)
                        .aspectRatio(1f))

            } else {
                BingoSphere(
                    sphereSize = 120.dp,
                    canvasRadius = 108f,
                    canvasStroke = 12f,
                    fontSize = 48.sp,
                    number = uiState.drawnNumbers.last().toString()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                DrawnText()
                if (uiState.drawnNumbers.isNotEmpty()) {
                    val numbers = uiState.drawnNumbers.dropLast(1).reversed()

                    DrawnSpheresLazyGrid(
                        drawnNumbers = numbers,
                        minSize = 48.dp,
                        itemsSpacing = 2.dp,
                        onClick = { onCopyDrawn() },
                        modifier = Modifier
                            .height(160.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            DrawerButtons(
                isFinished = uiState.isFinished,
                onDrawNextCharacter = { onDrawNextCharacter() },
                onFinishDraw = { onFinishDraw() },
                onStartNewDraw = { onStartNewDraw() })
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
            isFinished = true,
            drawnNumbers = (1..75).toList().shuffled().subList(0, 40),
            availableNumbers = (1..75).toList().shuffled().subList(0, 35),
            numbers = (1..75).toList()
        ),
        onDrawNextCharacter = { },
        onFinishDraw = { },
        onStartNewDraw = { },
        onCopyDrawn = { })
}