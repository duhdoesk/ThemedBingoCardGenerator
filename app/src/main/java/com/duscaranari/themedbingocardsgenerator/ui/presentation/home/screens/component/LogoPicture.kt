package com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun LogoPicture(
    maxWidth: Float,
    maxHeight: Float
) {
    Image(
        painter = painterResource(id = R.drawable.compact_screen_logo),
        contentDescription = stringResource(id = R.string.logo),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .fillMaxHeight(maxHeight)
            .shadow(16.dp)
    )
}