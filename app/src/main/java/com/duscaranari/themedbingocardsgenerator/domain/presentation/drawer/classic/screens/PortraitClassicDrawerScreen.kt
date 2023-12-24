package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.BingoSphere
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitClassicDrawerScreen() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

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
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "27 de 75")

            Spacer(modifier = Modifier.height(8.dp))

            BingoSphere(
                sphereSize = 120.dp,
                canvasRadius = 108f,
                canvasStroke = 12f,
                fontSize = 48.sp,
                number = "47"
            )

            BingoSphere(
                sphereSize = 60.dp,
                canvasRadius = 54f,
                canvasStroke = 6f,
                fontSize = 24.sp,
                number = "47"
            )
        }
    }
}


/**
 * PORTRAIT PREVIEWS
 */
@PortraitPreviews
@Composable
fun PortraitPreview() {
    PortraitClassicDrawerScreen()
}