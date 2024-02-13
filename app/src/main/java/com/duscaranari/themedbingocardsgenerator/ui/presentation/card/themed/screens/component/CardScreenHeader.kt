package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun CardScreenHeader(
    themeName: String,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {

        Text(
            text = stringResource(id = R.string.selected_theme) + ": " + themeName,
            style = MaterialTheme.typography.titleMedium
        )
    }
}