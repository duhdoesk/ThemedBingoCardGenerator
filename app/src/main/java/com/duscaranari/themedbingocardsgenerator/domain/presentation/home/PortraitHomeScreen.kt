package com.duscaranari.themedbingocardsgenerator.domain.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component.BingoSphere
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitHomeScreen(onNavigate: (route: String) -> Unit, subscribed: Boolean) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            ThemedLabels()

            Image(
                painter = painterResource(id = R.drawable.smiling_squirrel),
                contentDescription = "Smiling Squirrel",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(132.dp)
                    .padding(vertical = 16.dp)
            )

            ThemedBingoButtons(
                onNavigate = { onNavigate(it) },
                subscribed = subscribed,
                buttonModifier = Modifier
                    .width(200.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))


            ClassicLabels()

            ClassicBingoButtons(
                onNavigate = { onNavigate(it) },
                buttonModifier = Modifier.width(200.dp),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (!subscribed) {
            SubscriptionButton(onNavigate = { onNavigate(it) })
        }
    }
}


@PortraitPreviews
@Composable
fun PortraitHomeScreenPreview() {
    PortraitHomeScreen(onNavigate = { }, subscribed = true)
}