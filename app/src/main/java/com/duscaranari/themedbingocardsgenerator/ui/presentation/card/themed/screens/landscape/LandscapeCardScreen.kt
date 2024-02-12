package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.landscape

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.CardScreenUserButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.common.NewCardButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.CardScreenGrid
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.CardScreenHeader
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.SizeSelectorSwitchButton
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.RotateScreen
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun LandscapeCardScreen(
    onUpdateCurrentUser: (user: String) -> Unit,
    onDrawNewCard: () -> Unit,
    state: CardUiState.Success,
    onNavToCharactersScreen: () -> Unit,
    onChangeCardSize: (boolean: Boolean) -> Unit
) {

    when (rememberWindowInfo().screenHeightInfo) {
        is WindowInfo.WindowType.Compact -> {
            RotateScreen()
        }

        else -> {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
            {
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

                        CardScreenGrid(
                            characters = state.drawnCharacters,
                            cardSize = state.cardSize,
                            modifier = Modifier
                                .widthIn(max = 560.dp)
                                .clickable { onNavToCharactersScreen() }
                                .padding(bottom = 24.dp)
                        )

                        CardScreenUserButton(
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

                        SizeSelectorSwitchButton(
                            optionSelected = state.cardSize,
                            onClick = { onChangeCardSize(it) }
                        )
                    }
                }
            }
        }
    }
}