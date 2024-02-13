package com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.portrait

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.SubscriptionButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoTypePager
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.HomeScreenHeader

@Composable
fun CompactPortraitHomeScreen(
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean,
    bingoTypes: List<BingoType>,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 4.dp)
        ) {

            HomeScreenHeader()

            Spacer(modifier = Modifier.height(24.dp))

            BingoTypePager(
                bingoTypes = bingoTypes,
                modifier = Modifier
                    .fillMaxWidth(),
                onBingoTypeChange = { onBingoTypeChange(it) },
                onNavigate = { onNavigate(it) },
                isSubscribed = subscribed
            )
        }

        if (!subscribed) {
            SubscriptionButton(
                onNavigate = { onNavigate(it) },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}