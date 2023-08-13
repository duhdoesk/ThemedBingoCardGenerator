package com.duscaranari.themedbingocardsgenerator.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

/**
 * Composable to load images from the backend, while displaying a loader.
 *
 * @param model - Either an ImageRequest or the ImageRequest.data value.
 * @param contentDescription - text used by accessibility services to describe what this image represents.
 * @param modifier - Modifier used to adjust the layout algorithm or draw decoration content (ex. background)
 * @param alignment - Optional alignment parameter used to place the Painter in the given bounds defined by the width and height.
 * @param contentScale - Optional scale parameter used to determine the aspect ratio scaling to be used if the bounds are a different size from the intrinsic size of the Painter
 * @param alpha - Optional opacity to be applied to the Painter when it is rendered onscreen the default renders the Painter completely opaque
 * @param colorFilter - Optional colorFilter to apply for the Painter when it is rendered onscreen
 * @param loader - Optional loader to display while image is loading -- default value set to a basic circular progress indicator
 * */
@Composable
fun LoadingImage(
    model: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    loader: @Composable () -> Unit = { CircularProgressIndicator() }
) {
    val painter = rememberAsyncImagePainter(model)
    if (painter.state is AsyncImagePainter.State.Loading) {
        loader()
    }
    Image(
        modifier = modifier,
        painter = painter,
        contentScale = contentScale,
        contentDescription = contentDescription,
        alignment = alignment,
        alpha = alpha,
        colorFilter = colorFilter
    )
}