package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.landscape

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.SubscriptionButton
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoTypePager
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.HomeScreenHeader
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.LogoPicture
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.card.BingoTypeCard

@Composable
fun MediumLandscapeHomeScreen(
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean,
    bingoTypes: List<BingoType>,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                HomeScreenHeader()

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        alignment = Alignment.CenterHorizontally,
                        space = 12.dp
                    ),
                    modifier = Modifier
                        .widthIn(max = 800.dp)
                ) {
                    for (bingo in bingoTypes) {
                        BingoTypeCard(
                            bingoType = bingo,
                            onNavigate = {
                                onBingoTypeChange(bingo)
                                onNavigate(it)
                            },
                            isSubscribed = subscribed,
                            cardModifier = Modifier.weight(1f),
                            pictureModifier = Modifier.fillMaxWidth(0.5f),
                            buttonModifier = Modifier.width(200.dp)
                        )
                    }
                }
            }

            if (!subscribed) {
                SubscriptionButton(
                    onNavigate = { onNavigate(it) },
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}