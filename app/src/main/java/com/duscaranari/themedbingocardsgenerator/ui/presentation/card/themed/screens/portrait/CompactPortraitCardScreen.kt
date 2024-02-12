package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.portrait

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component.ThemedCardSizeSwitcher
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.event.ThemedCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState

@Composable
fun CompactPortraitCardScreen(
    state: CardUiState.Success,
    event: (event: ThemedCardUiEvent) -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        ThemedCardSizeSwitcher(
            cardSize = state.cardSize,
            onClick = { event(ThemedCardUiEvent.OnChangeCardSize) },
            parentShape = RoundedCornerShape(32.dp),
            toggleShape = RoundedCornerShape(32.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(40.dp)
        )
    }
}