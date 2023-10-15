package com.duscaranari.themedbingocardsgenerator.domain.presentation.component

import android.content.Context
import android.os.Build
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

fun getImageLoader(context: Context): ImageLoader {
    return ImageLoader
        .Builder(context)
        .components {
            when {
                Build.VERSION.SDK_INT >= 28 -> {
                    add(ImageDecoderDecoder.Factory())
                }

                else -> {
                    add(GifDecoder.Factory())
                }
            }
        }
        .build()
}