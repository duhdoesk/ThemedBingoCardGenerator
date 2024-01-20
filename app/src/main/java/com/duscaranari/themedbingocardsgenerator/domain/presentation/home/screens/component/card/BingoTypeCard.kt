package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.card

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType.*
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getClassicBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getOnlineBingoColorScheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.bingoColorSchemes.getThemedBingoColorScheme

@Composable
fun BingoTypeCard(
    bingoType: BingoType,
    onNavigate: (route: String) -> Unit,
    @SuppressLint("ModifierParameter")
    cardModifier: Modifier = Modifier,
    buttonModifier: Modifier = Modifier,
    pictureModifier: Modifier = Modifier,
    isSubscribed: Boolean
) {

    val topDestination = bingoType.topDestination.name
    val bottomDestination = bingoType.bottomDestination.name
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

                BingoTypeCardName(
                    colorScheme = colorScheme,
                    bingoType = bingoType
                )

                Spacer(modifier = Modifier.height(24.dp))

                BingoTypeCardButton(
                    onNavigate = {
                        if (bingoType.topIsPremium && !isSubscribed)
                            onNavigate(AppScreens.Subs.name)
                        else
                            onNavigate(topDestination)
                    },
                    bingoType = bingoType,
                    isPremium = bingoType.topIsPremium,
                    text = stringResource(id = bingoType.topLabel),
                    modifier = buttonModifier,
                    containerColor = colorScheme.primaryContainer,
                    contentColor = colorScheme.onPrimaryContainer,
                    isSubscribed = isSubscribed
                )

                BingoTypeCardButton(
                    onNavigate = {
                        if (bingoType.bottomIsPremium && !isSubscribed)
                            onNavigate(AppScreens.Subs.name)
                        else
                            onNavigate(bottomDestination)
                    },
                    bingoType = bingoType,
                    isPremium = bingoType.bottomIsPremium,
                    text = stringResource(id = bingoType.bottomLabel),
                    modifier = buttonModifier,
                    containerColor = colorScheme.primary,
                    contentColor = colorScheme.onPrimary,
                    isSubscribed = isSubscribed
                )
            }

            if (!bingoType.enabled) {
                BingoTypeCardSoonMark(
                    colorScheme = colorScheme,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                )
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
        onNavigate = { },
        isSubscribed = false
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
        onNavigate = { },
        isSubscribed = false
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
        onNavigate = { },
        isSubscribed = false
    )
}
