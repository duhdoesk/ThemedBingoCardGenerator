package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.event.ClassicCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.component.ClassicCardGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.component.getRandomCard
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.CardScreenUserButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.NewCardButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onEvent: (event: ClassicCardUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact ->
            RotateScreen()

        is WindowInfo.WindowType.Medium ->
            MediumLandscapeClassicScreen(
                uiState = uiState,
                onEvent = { onEvent(it) })

        else ->
            ExpandedLandscapeClassicScreen(
                uiState = uiState,
                onEvent = { onEvent(it) })
    }
}


@LandscapePreviews
@Composable
fun LandscapeClassicCardScreenPreview() {
    LandscapeClassicCardScreen(
        uiState = ClassicCardUiState.Ready(
            numbers = getRandomCard(),
            currentUser = "Ronaldo"
        ),
        onEvent = { }
    )
}