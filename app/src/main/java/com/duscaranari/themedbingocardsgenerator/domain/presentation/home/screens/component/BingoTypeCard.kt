package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType.*
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getClassicBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getOnlineBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getThemedBingoColorScheme

@Composable
fun BingoTypeCard(
    bingoType: BingoType,
    onClick: (destination: Int) -> Unit,
    @SuppressLint("ModifierParameter")
    cardModifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    pictureModifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    val colorScheme = when (bingoType) {
        THEMED -> getThemedBingoColorScheme()
        CLASSIC -> getClassicBingoColorScheme()
        ONLINE -> getOnlineBingoColorScheme()
    }

    Card(
        modifier = cardModifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
    ) {

        Box(
            modifier = Modifier
                .paint(
                    painterResource(id = bingoType.background),
                    contentScale = ContentScale.Crop
                )
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = bingoType.avatar),
                    contentDescription = "Bingo Type Avatar",
                    modifier = pictureModifier
                )

                Box {
                    Text(
                        text = bingoType.name.lowercase(),
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorScheme.primary
                    )

                    Text(
                        text = bingoType.name.lowercase(),
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier.offset((-2).dp)
                    )
                }


                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onClick(bingoType.cardDestination.stringResource) },
                    enabled = enabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.primaryContainer,
                        contentColor = colorScheme.onPrimaryContainer
                    ),
                    modifier = buttonModifier
                ) {
                    Text(text = stringResource(id = R.string.card_screen))
                }

                Button(
                    onClick = { onClick(bingoType.drawerDestination.stringResource) },
                    enabled = enabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.primary,
                        contentColor = colorScheme.onPrimary
                    ),
                    modifier = buttonModifier
                ) {
                    Text(text = stringResource(id = R.string.drawer_screen))
                }
            }

            if (!enabled) {
                Box(
                    contentAlignment = Alignment.BottomEnd,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                ) {

                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(12.dp)
                    ) {

                        drawRect(
                            color = colorScheme.primary
                        )
                    }

                    Text(
                        text = stringResource(id = R.string.soon),
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Black,
                        color = colorScheme.primary,
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )

                    Text(
                        text = stringResource(id = R.string.soon),
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset((-2).dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun BingoTypeCardOnlinePreview() {
    BingoTypeCard(
        bingoType = ONLINE,
        cardModifier = Modifier.width(240.dp),
        buttonModifier = Modifier.width(200.dp),
        pictureModifier = Modifier.size(120.dp),
        onClick = { },
        enabled = false
    )
}

@Preview
@Composable
fun BingoTypeCardThemedPreview() {
    BingoTypeCard(
        bingoType = THEMED,
        cardModifier = Modifier.width(240.dp),
        buttonModifier = Modifier.width(200.dp),
        pictureModifier = Modifier.size(120.dp),
        onClick = { }
    )
}

@Preview
@Composable
fun BingoTypeCardClassicPreview() {
    BingoTypeCard(
        bingoType = CLASSIC,
        cardModifier = Modifier.width(240.dp),
        buttonModifier = Modifier.width(200.dp),
        pictureModifier = Modifier.size(120.dp),
        onClick = { }
    )
}
