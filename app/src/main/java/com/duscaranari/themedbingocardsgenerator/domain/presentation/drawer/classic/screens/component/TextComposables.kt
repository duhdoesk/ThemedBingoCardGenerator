package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun ClassicBingoHeadline() =
    Text(
        text = stringResource(id = R.string.classic_bingo),
        fontFamily = FontFamily.Cursive,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold)


@Composable
fun DrawingCounter(total: String, drawn: String) =
    Text(text = "$drawn / $total")

@Composable
fun DrawnText() =
    Text(text = stringResource(id = R.string.drawn))