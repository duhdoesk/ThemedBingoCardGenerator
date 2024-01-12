package com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicDarkOnPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicDarkOnPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicDarkPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicDarkPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicLightOnPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicLightOnPrimaryContainer
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicLightPrimary
import com.duscaranari.themedbingocardsgenerator.ui.theme.ClassicLightPrimaryContainer

val ClassicBingoLightColorScheme = lightColorScheme(
    primary = ClassicLightPrimary,
    onPrimary = ClassicLightOnPrimary,
    primaryContainer = ClassicLightPrimaryContainer,
    onPrimaryContainer = ClassicLightOnPrimaryContainer
)

val ClassicBingoDarkColorScheme = darkColorScheme(
    primary = ClassicDarkPrimary,
    onPrimary = ClassicDarkOnPrimary,
    primaryContainer = ClassicDarkPrimaryContainer,
    onPrimaryContainer = ClassicDarkOnPrimaryContainer
)

@Composable
fun getClassicBingoColorScheme(): ColorScheme =
    if (isSystemInDarkTheme()) ClassicBingoDarkColorScheme else ClassicBingoLightColorScheme