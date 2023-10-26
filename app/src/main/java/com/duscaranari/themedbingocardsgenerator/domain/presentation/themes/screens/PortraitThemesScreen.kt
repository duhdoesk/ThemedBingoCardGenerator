package com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd

@Composable
fun PortraitThemesScreen(
    onNavigate: (themeId: String) -> Unit,
    themes: List<Theme>,
    subscribed: Boolean,
    context: Context
    ) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .sizeIn(
                    maxWidth = 600.dp,
                    maxHeight = 1000.dp
                )
                .padding(8.dp)
        ) {

            TopLabel(modifier = Modifier.padding(horizontal = 16.dp))

            ThemesLazyVerticalGrid(
                themes = themes,
                minSize = 120.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = { theme ->

                    if (!subscribed) {
                        showInterstitialAd(context)
                    }

                    onNavigate(theme.themeId)
                })
        }
    }
}