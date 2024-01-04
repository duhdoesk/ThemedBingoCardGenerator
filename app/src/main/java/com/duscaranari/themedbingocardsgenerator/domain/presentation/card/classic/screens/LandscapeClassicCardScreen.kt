package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.ClassicCardUiState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.component.ClassicCardGrid
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.component.getRandomCard
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.CardScreenName
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.screens.NewCardButton
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeClassicCardScreen(
    uiState: ClassicCardUiState.Ready,
    onDrawNewCard: () -> Unit,
    onUpdateCurrentUser: (user: String) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }

        else -> {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .sizeIn(maxWidth = 1000.dp, maxHeight = 600.dp)
                ) {

                    ClassicCardGrid(
                        numbers = uiState.numbers,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            alignment = Alignment.CenterVertically,
                            space = 32.dp
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {

                        CardScreenName(
                            onChange = { onUpdateCurrentUser(it) },
                            currentUser = uiState.currentUser
                        )

                        NewCardButton(
                            onClick = { onDrawNewCard() }
                        )
                    }
                }
            }
        }
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
        onDrawNewCard = { },
        onUpdateCurrentUser = { }
    )
}