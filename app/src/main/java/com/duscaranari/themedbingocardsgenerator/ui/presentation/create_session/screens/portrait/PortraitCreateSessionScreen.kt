package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event.CreateSessionEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.LimitOfWinnersSlider
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.SessionNameComponent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.SessionPasswordComponent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.SessionThemeSelector
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState

@Composable
fun PortraitCreateSessionScreen(
    uiState: CreateSessionUiState,
    themes: List<BingoTheme>,
    isFormOk: Boolean,
    event: (event: CreateSessionEvent) -> Unit
) {
    Column {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {

            val leadingIconModifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))

            val contentScale = ContentScale.Crop

            SessionNameComponent(
                uiState = uiState,
                onUpdateName = { event(CreateSessionEvent.OnUpdateName(it)) },
                modifier = Modifier,
                leadingIconModifier = leadingIconModifier
            )

            SessionThemeSelector(
                uiState = uiState,
                themes = themes,
                contentScale = contentScale,
                onUpdateThemeId = { event(CreateSessionEvent.OnUpdateThemeId(it)) },
                modifier = Modifier,
                leadingIconModifier = leadingIconModifier
            )

            LimitOfWinnersSlider(
                onValueChangeFinished = { event(CreateSessionEvent.OnUpdateLimitOfWinners(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                leadingIconModifier = leadingIconModifier
            )

            SessionPasswordComponent(
                uiState = uiState,
                onUpdateLockedState = { event(CreateSessionEvent.OnUpdateLockState(it)) },
                onUpdatePassword = { event(CreateSessionEvent.OnUpdatePassword(it)) },
                modifier = Modifier,
                leadingIconModifier = leadingIconModifier
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Button(
                enabled = isFormOk,
                onClick = { event(CreateSessionEvent.OnCreateNewSession) },
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Create")
            }
        }
    }
}