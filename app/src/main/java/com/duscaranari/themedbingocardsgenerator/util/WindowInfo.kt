package com.duscaranari.themedbingocardsgenerator.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
@Composable
fun rememberWindowInfo(): WindowInfo {

    val config = LocalConfiguration.current

    return WindowInfo(
        screenWidthInfo = when {
            config.screenWidthDp < 600 -> WindowInfo.WindowType.Compact
            config.screenWidthDp < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },

        screenHeightInfo = when {
            config.screenHeightDp < 480 -> WindowInfo.WindowType.Compact
            config.screenHeightDp < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },

        screenWidth = config.screenWidthDp.dp,
        screenHeight = config.screenHeightDp.dp
    )
}


data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp
) {

    sealed class WindowType {

        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}
