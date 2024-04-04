package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.portrait

import androidx.compose.runtime.Composable
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.event.ThemedCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.getRawListOfCharacters
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun PortraitCardScreen(
    state: CardUiState.Success,
    event: (event: ThemedCardUiEvent) -> Unit
) {

    when (rememberWindowInfo().screenWidthInfo) {
        is WindowInfo.WindowType.Compact ->
            CompactPortraitCardScreen(
                state = state,
                event = { event(it) })

        else ->
            ExpandedPortraitCardScreen(
                state = state,
                event = { event(it) }
            )
    }
}