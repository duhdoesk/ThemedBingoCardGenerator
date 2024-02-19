package com.duscaranari.themedbingocardsgenerator.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType.CLASSIC
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType.THEMED
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getClassicBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getOnlineBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getThemedBingoColorScheme

@Composable
fun ThemedBingoCardsGeneratorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    bingoType: BingoType,
    content: @Composable () -> Unit
) {

    val colorScheme = when (bingoType) {
        CLASSIC -> getClassicBingoColorScheme()
        THEMED -> getThemedBingoColorScheme()
        else -> getOnlineBingoColorScheme()
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}