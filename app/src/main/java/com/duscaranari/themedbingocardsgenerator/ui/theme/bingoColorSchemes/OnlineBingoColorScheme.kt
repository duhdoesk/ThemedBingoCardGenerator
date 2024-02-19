package com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineDarkOnPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineDarkOnPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineDarkPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineDarkPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineLightOnPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineLightOnPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineLightPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.OnlineLightPrimaryContainer

val OnlineBingoLightColorScheme = lightColorScheme(
    primary = OnlineLightPrimary,
    onPrimary = OnlineLightOnPrimary,
    primaryContainer = OnlineLightPrimaryContainer,
    onPrimaryContainer = OnlineLightOnPrimaryContainer
)

val OnlineBingoDarkColorScheme = darkColorScheme(
    primary = OnlineDarkPrimary,
    onPrimary = OnlineDarkOnPrimary,
    primaryContainer = OnlineDarkPrimaryContainer,
    onPrimaryContainer = OnlineDarkOnPrimaryContainer
)

@Composable
fun getOnlineBingoColorScheme(): ColorScheme =
    if (isSystemInDarkTheme()) OnlineBingoDarkColorScheme else OnlineBingoLightColorScheme