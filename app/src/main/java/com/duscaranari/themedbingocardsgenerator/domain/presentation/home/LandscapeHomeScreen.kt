package com.duscaranari.themedbingocardsgenerator.domain.presentation.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd

@Composable
fun LandscapeHomeScreen(
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean,
    context: Context
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }

        else -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                LogoPicture(
                    maxWidth = 0.4f,
                    maxHeight = 1f
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {

                    ThemedLabels()

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {

                        ThemedBingoButtons(
                            modifier = Modifier.fillMaxWidth(),
                            onNavigate = { onNavigate(it) },
                            subscribed = subscribed
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))

                    ClassicLabels()

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {

                        ClassicBingoButtons(
                            modifier = Modifier.fillMaxWidth(),
                            onNavigate = {
                                if (!subscribed) {
                                    showInterstitialAd(context)
                                }
                                onNavigate(it)
                            }
                        )
                    }

                    if (!subscribed) {
                        SubscriptionButton(onNavigate = { onNavigate(it) })
                    }
                }
            }
        }
    }
}


@LandscapePreviews
@Composable
fun LandscapeHomeScreenPreview() {
    val context = LocalContext.current
    LandscapeHomeScreen(onNavigate = { }, subscribed = true, context = context)
}