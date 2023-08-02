package com.duscaranari.themedbingocardsgenerator.util

import androidx.compose.runtime.Composable

@Composable
fun rememberDeviceOrientation(): DeviceOrientation {

    return when {

        rememberWindowInfo().screenWidth > rememberWindowInfo().screenHeight ->
            DeviceOrientation.Landscape

        else ->
            DeviceOrientation.Portrait
    }
}

sealed class DeviceOrientation {
    object Portrait: DeviceOrientation()
    object Landscape: DeviceOrientation()
}