package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getClassicBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getOnlineBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getThemedBingoColorScheme

@Composable
fun BingoTypeCard(
    bingoType: BingoType,
    cardModifier: Modifier = Modifier,
    onClick: (destination: Int) -> Unit,
    buttonModifier: Modifier = Modifier,
    pictureModifier: Modifier = Modifier,
    colorScheme: ColorScheme,
    picture: Int
) {


    Card(modifier = cardModifier
        .width(230.dp)
        .border(
            width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(12.dp)
        )) {

        Box(
            modifier = Modifier
                .paint(
                    painterResource(id = picture),
                    contentScale = ContentScale.Crop
                )
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = bingoType.picture),
                    contentDescription = "Bingo Type Picture",
                    modifier = pictureModifier
                )

                Text(
                    text = bingoType.name.lowercase(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.ExtraBold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { onClick(bingoType.cardDestination.stringResource) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.surface,
                        contentColor = colorScheme.primary
                    ),
                    modifier = buttonModifier
                ) {
                    Text(text = stringResource(id = R.string.card_screen))
                }

                Button(
                    onClick = { onClick(bingoType.drawerDestination.stringResource) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorScheme.primaryContainer,
                        contentColor = colorScheme.onPrimaryContainer
                    ),
                    modifier = buttonModifier
                ) {
                    Text(text = stringResource(id = R.string.drawer_screen))
                }
            }
        }
    }
}


@Preview
@Composable
fun BingoTypeCardOnlinePreview() {
    BingoTypeCard(
        bingoType = BingoType.ONLINE,
        cardModifier = Modifier,
        buttonModifier = Modifier.width(200.dp),
        pictureModifier = Modifier.size(120.dp),
        onClick = { },
        colorScheme = getOnlineBingoColorScheme(),
        picture = R.drawable.blue_water
    )
}

@Preview
@Composable
fun BingoTypeCardThemedPreview() {
    BingoTypeCard(
        bingoType = BingoType.THEMED,
        cardModifier = Modifier,
        buttonModifier = Modifier.width(200.dp),
        pictureModifier = Modifier.size(120.dp),
        onClick = { },
        colorScheme = getThemedBingoColorScheme(),
        picture = R.drawable.green_water
    )
}

@Preview
@Composable
fun BingoTypeCardClassicPreview() {
    BingoTypeCard(
        bingoType = BingoType.CLASSIC,
        cardModifier = Modifier,
        buttonModifier = Modifier.width(200.dp),
        pictureModifier = Modifier.size(120.dp),
        onClick = { },
        colorScheme = getClassicBingoColorScheme(),
        picture = R.drawable.orange_water
    )
}
