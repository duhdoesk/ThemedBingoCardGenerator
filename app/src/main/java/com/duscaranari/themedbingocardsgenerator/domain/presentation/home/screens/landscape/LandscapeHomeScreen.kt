package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.landscape

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeHomeScreen(
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean,
    bingoTypes: List<BingoType>,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }

        else -> {
            MediumLandscapeHomeScreen(
                onNavigate = { onNavigate(it) },
                subscribed = subscribed,
                bingoTypes = bingoTypes,
                onBingoTypeChange = { onBingoTypeChange(it) }
            )
        }
    }
}


@LandscapePreviews
@Composable
fun LandscapeHomeScreenPreview() {
    LandscapeHomeScreen(
        onNavigate = { },
        subscribed = false,
        onBingoTypeChange = { },
        bingoTypes = BingoType.getBingoTypes())
}