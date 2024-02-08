package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.event.ClassicCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.component.getRandomCard
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onEvent: (event: ClassicCardUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {
        is WindowInfo.WindowType.Compact ->
            CompactPortraitClassicCardScreen(
                uiState = uiState,
                onEvent = { onEvent(it) })

        else ->
            ExpandedPortraitClassicCardScreen(
                uiState = uiState,
                onEvent = { onEvent(it) })
    }
}


@PortraitPreviews
@Composable
fun PortraitClassicCardScreenPreview() {
    PortraitClassicCardScreen(
        uiState = ClassicCardUiState.Ready(
            numbers = getRandomCard(),
            currentUser = "Mortadelo"
        ),
        onEvent = {  }
    )
}