package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.portrait

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.CardScreenUserButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.NewCardButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.CardScreenGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.CardScreenHeader
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.SizeSelectorSwitchButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.event.ThemedCardUiEvent
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


@PortraitPreviews
@Composable
fun PortraitPreview() {

    val characters = getRawListOfCharacters()

    PortraitCardScreen(
        state = CardUiState.Success(
            currentTheme = Theme(
                themeId = "1",
                themeName = "Bears",
                themePicture = ""
            ),
            currentUser = "Dwight Schrute",
            drawnCharacters = characters,
            themeCharacters = characters,
            cardSize = CardSize.LARGE
        ),
        event = {  }
    )
}