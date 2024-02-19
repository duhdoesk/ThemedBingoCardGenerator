package com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedDarkOnPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedDarkOnPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedDarkPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedDarkPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedLightOnPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedLightOnPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedLightPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedLightPrimaryContainer

val ThemedBingoLightColorScheme = lightColorScheme(
    primary = ThemedLightPrimary,
    onPrimary = ThemedLightOnPrimary,
    primaryContainer = ThemedLightPrimaryContainer,
    onPrimaryContainer = ThemedLightOnPrimaryContainer
)

val ThemedBingoDarkColorScheme = darkColorScheme(
    primary = ThemedDarkPrimary,
    onPrimary = ThemedDarkOnPrimary,
    primaryContainer = ThemedDarkPrimaryContainer,
    onPrimaryContainer = ThemedDarkOnPrimaryContainer
)

@Composable
fun getThemedBingoColorScheme(): ColorScheme =
    if (isSystemInDarkTheme()) ThemedBingoDarkColorScheme else ThemedBingoLightColorScheme