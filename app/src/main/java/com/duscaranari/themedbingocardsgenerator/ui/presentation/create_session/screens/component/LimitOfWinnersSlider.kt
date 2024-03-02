package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun LimitOfWinnersSlider(
    onValueChangeFinished: (value: Float) -> Unit,
    modifier: Modifier = Modifier,
    leadingIconModifier: Modifier
) {

    val color by remember {
        mutableStateOf(
            Color(
                blue = Random.nextInt(160, 256),
                red = Random.nextInt(160, 256),
                green = Random.nextInt(160, 256),
                alpha = 255
            )
        )
    }

    var sliderPosition by remember { mutableFloatStateOf(1f) }

    Row(modifier = modifier) {

        Box(modifier = leadingIconModifier) {
            Surface(
                color = color,
                modifier = Modifier
                    .fillMaxSize()
            ) {}

            Text(
                text = "${sliderPosition.roundToInt()}",
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Black,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {


            Text(
                text = "Limit of Winners",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "1",
                    textAlign = TextAlign.Center
                )

                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        Log.d("SliderPos", sliderPosition.roundToInt().toString())
                    },
                    valueRange = 1f..16f,
                    modifier = Modifier.weight(1f),
                    onValueChangeFinished = { onValueChangeFinished(sliderPosition) }
                )

                Text(
                    text = "16",
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}