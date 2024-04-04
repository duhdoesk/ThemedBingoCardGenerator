package com.duscaranari.themedbingocardsgenerator.ui.presentation.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.HomeScreenHeader
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun SignInScreen(
    onSignIn: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically,
            space = 60.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        HomeScreenHeader()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterVertically,
                space = 12.dp
            )
        ) {

            Text(
                text = stringResource(id = R.string.sign_in_required),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(id = R.string.log_in_text)
            )
        }

        Button(
            onClick = onSignIn,
            modifier = Modifier.width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }
    }
}

@PortraitPreviews
@Composable
fun Preview() {
    SignInScreen {

    }
}