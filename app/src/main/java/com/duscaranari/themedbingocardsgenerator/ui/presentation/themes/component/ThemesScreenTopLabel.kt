package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun ThemesScreenTopLabel(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {

        Text(
            text = stringResource(id = R.string.available_themes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}