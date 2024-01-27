package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.classic.screens.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.theme.SphereBrown
import com.duscaranari.themedbingocardsgenerator.ui.theme.SphereDetail

@Composable
fun BingoSphere(
    sphereSize: Dp,
    canvasRadius: Float,
    canvasStroke: Float,
    fontSize: TextUnit,
    number: String
) {

    val color1 = SphereDetail
    val color2 = SphereBrown

    Box(contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource(id = R.drawable.sphere),
            contentDescription = "Bingo Sphere",
            modifier = Modifier
                .widthIn(max = sphereSize)
                .aspectRatio(1f)
        )

        Canvas(
            modifier = Modifier
                .size(sphereSize)
                .offset(
                    x = (-0.7).dp,
                    y = (-0.7).dp
                )
                .alpha(0.7f),
            onDraw = {
                drawCircle(
                    color = color1,
                    style = Stroke(width = canvasStroke),
                    radius = canvasRadius
                )
            })

        Canvas(
            modifier = Modifier.size(sphereSize),
            onDraw = {
                drawCircle(
                    color = color2,
                    style = Stroke(width = canvasStroke),
                    radius = canvasRadius
                )
            })

        Text(
            text = number,
            fontFamily = FontFamily.SansSerif,
            fontSize = fontSize,
            fontWeight = FontWeight.W700,
            color = color1,
            modifier = Modifier
                .offset(
                    x = (-0.7).dp,
                    y = (-0.7).dp
                )
                .alpha(0.7f)
        )

        Text(
            text = number,
            color = color2,
            fontFamily = FontFamily.SansSerif,
            fontSize = fontSize,
            fontWeight = FontWeight.W700
        )
    }
}