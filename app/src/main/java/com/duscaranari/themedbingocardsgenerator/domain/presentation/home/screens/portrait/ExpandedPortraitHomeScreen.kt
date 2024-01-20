package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType

@Composable
fun ExpandedPortraitHomeScreen(
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean,
    bingoTypes: List<BingoType>,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    MediumPortraitHomeScreen(
        onNavigate = { onNavigate(it) },
        subscribed = subscribed,
        bingoTypes = bingoTypes,
        onBingoTypeChange = onBingoTypeChange
    )
}