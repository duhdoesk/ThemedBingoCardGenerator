package com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd

@Composable
fun LandscapeThemesScreen(
    onNavigate: (themeId: String) -> Unit,
    themes: List<Theme>,
    subscribed: Boolean,
    context: Context
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }
        else -> {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Column(
                    modifier = Modifier
                        .sizeIn(
                            maxWidth = 1000.dp,
                            maxHeight = 600.dp
                        )
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {

                    Row {
                        TopLabel()
                    }

                    Spacer(Modifier.height(8.dp))

                    Row {

                        ThemesLazyVerticalGrid(
                            themes = themes,
                            minSize = 120.dp,
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f),
                            onClick = { theme ->

                                if (!subscribed) {
                                    showInterstitialAd(context)
                                }

                                onNavigate(theme.themeId)
                            })

                        Image(
                            painter = painterResource(id = R.drawable.smiling_tiger),
                            contentDescription = "Smiling tiger",
                            modifier = Modifier.size(160.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }
    }
}