package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.event.ClassicCardUiEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.component.ClassicCardGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.state.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.CardScreenUserButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.NewCardButton
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun MediumLandscapeClassicScreen(
    uiState: ClassicCardUiState.Ready,
    onEvent: (event: ClassicCardUiEvent) -> Unit
) {

    val height = rememberWindowInfo().screenHeight

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .width(height.times((0.6).toFloat()))
        ) {

            ClassicCardGrid(
                numbers = uiState.numbers,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
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

//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
//        modifier = Modifier
//            .fillMaxSize()
//            .sizeIn(
//                maxWidth = 1200.dp,
//                maxHeight = 600.dp
//            )
//    ) {
//
//        Column(
//            verticalArrangement = Arrangement.spacedBy(
//                alignment = Alignment.CenterVertically,
//                space = 12.dp
//            ),
//            horizontalAlignment = Alignment.End,
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxHeight()
//        ) {
//            ClassicCardGrid(
//                numbers = uiState.numbers,
//                modifier = Modifier
//                    .fillMaxWidth(0.8f)
//            )
//
//            CardScreenUserButton(
//                onChange = { onEvent(ClassicCardUiEvent.OnUpdateCurrentUser(it)) },
//                currentUser = uiState.currentUser,
//                modifier = Modifier
//                    .fillMaxWidth(0.8f)
//            )
//        }
//
//        Column(
//            verticalArrangement = Arrangement.spacedBy(
//                alignment = Alignment.CenterVertically,
//                space = 32.dp
//            ),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxHeight()
//        ) {
//
//            NewCardButton(
//                onClick = { onEvent(ClassicCardUiEvent.OnDrawNewCard) },
//                modifier = Modifier
//                    .width(200.dp)
//            )
//        }
//    }
}