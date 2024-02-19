package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.screens.landscape

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun ExpandedLandscapeClassicScreen(
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
                .width(height.times((0.65).toFloat()))
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

//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(
//                space = 16.dp,
//                alignment = Alignment.CenterVertically
//            ),
//            modifier = Modifier
//                .widthIn(min = 400.dp, max = 800.dp)
//                .fillMaxHeight()
//        ) {
//
//            ClassicCardGrid(
//                numbers = uiState.numbers,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .padding(horizontal = 16.dp)
//                    .fillMaxWidth()
//            ) {
//
//                CardScreenUserButton(
//                    onChange = { onEvent(ClassicCardUiEvent.OnUpdateCurrentUser(it)) },
//                    currentUser = uiState.currentUser,
//                    modifier = Modifier.weight(1f),
//                )
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                NewCardButton(
//                    onClick = { onEvent(ClassicCardUiEvent.OnDrawNewCard) }
//                )
//            }
//        }
//    }
}