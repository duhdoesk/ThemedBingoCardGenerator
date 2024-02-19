package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.event.ClassicCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.component.ClassicCardGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.CardScreenUserButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.NewCardButton

@Composable
fun ExpandedPortraitClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onEvent: (event: ClassicCardUiEvent) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier.fillMaxSize()
    ) {

        ClassicCardGrid(
            numbers = uiState.numbers,
            modifier = Modifier
                .fillMaxWidth(0.7f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.7f)
        ) {

            CardScreenUserButton(
                onChange = { onEvent(ClassicCardUiEvent.OnUpdateCurrentUser(it)) },
                currentUser = uiState.currentUser,
                modifier = Modifier.weight(1f),
            )

            Spacer(modifier = Modifier.width(16.dp))

            NewCardButton(
                onClick = { onEvent(ClassicCardUiEvent.OnDrawNewCard) }
            )
        }
    }
}