package com.duscaranari.themedbingocardsgenerator.ui.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun ErrorScreen(
    errorMessage: Int,
    onTryAgain: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.sad_tiger),
            contentDescription = "Sad Tiger",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(240.dp)
        )

        Spacer(Modifier.height(12.dp))

        Text(
            text = stringResource(id = errorMessage),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        Button(onClick = onTryAgain) {
            Text(text = stringResource(id = R.string.try_again))
        }
    }
}


/**
 * PREVIEW
 */

@PortraitPreviews
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        errorMessage = R.string.draw_error,
        onTryAgain = { })
}