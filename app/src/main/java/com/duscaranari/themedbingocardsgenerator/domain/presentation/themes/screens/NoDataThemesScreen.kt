package com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun NoDataThemesScreen(
    onRefresh: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.sad_tiger),
            contentDescription = stringResource(id = R.string.sad_cartoon_tiger_caption),
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(240.dp)
        )

        Text(
            text = stringResource(id = R.string.no_data_message),
            textAlign = TextAlign.Center,
            modifier = Modifier.width(300.dp)
        )

        Button(
            onClick = { onRefresh() }
        ) {
            Text(stringResource(id = R.string.refresh))
        }
    }
}