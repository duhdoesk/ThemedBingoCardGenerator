package com.duscaranari.themedbingocardsgenerator.ui.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun RotateScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_screen_rotation_24),
                contentDescription = "Screen Rotation Icon",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 12.dp)
            )

            Text(
                text = stringResource(id = R.string.rotate_your_device),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}