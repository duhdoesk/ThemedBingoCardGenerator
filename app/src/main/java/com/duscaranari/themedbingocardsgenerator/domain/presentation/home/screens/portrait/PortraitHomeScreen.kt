package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitHomeScreen(
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean,
    bingoTypes: List<BingoType>,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Expanded -> ExpandedPortraitHomeScreen(
            onNavigate = { onNavigate(it) },
            subscribed = subscribed,
            bingoTypes = bingoTypes,
            onBingoTypeChange = onBingoTypeChange
        )

        else -> MediumPortraitHomeScreen(
            onNavigate = { onNavigate(it) },
            subscribed = subscribed,
            bingoTypes = bingoTypes,
            onBingoTypeChange = onBingoTypeChange
        )
    }
}


@PortraitPreviews
@Composable
fun PortraitHomeScreenPreview() {
    PortraitHomeScreen(
        onNavigate = { },
        subscribed = true,
        onBingoTypeChange = { },
        bingoTypes = BingoType.getBingoTypes())
}