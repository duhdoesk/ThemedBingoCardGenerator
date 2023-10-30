package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.CardState
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeCardScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardState.Ready,
    onNavToCharactersScreen: () -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }

        else -> {

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                ) {

                    CardScreenLazyHorizontalGrid(
                        characters = state.drawnCharacters,
                        rows = 3,
                        spacing = 8.dp,
                        modifier = Modifier
                            .padding(16.dp)
                            .heightIn(max = 600.dp)
                            .clickable { onNavToCharactersScreen() },
                        cardModifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f),
                    )

                    CardScreenName(
                        onChange = { onUpdateCurrentUser(it) },
                        currentUser = state.currentUser
                    )

                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                    modifier = Modifier
                ) {

                    CardScreenHeader(theme = state.currentTheme)

                    NewCardButton(
                        onClick = { onDrawNewCard() }
                    )
                }
            }
        }
    }
}