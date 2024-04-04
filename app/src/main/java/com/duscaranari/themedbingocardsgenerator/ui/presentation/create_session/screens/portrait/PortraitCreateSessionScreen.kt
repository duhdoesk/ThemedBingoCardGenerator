package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.portrait

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.event.CreateSessionEvent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.LimitOfWinnersSlider
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.SessionNameComponent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.SessionPasswordComponent
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component.SessionThemeSelector
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.mockSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitCreateSessionScreen(
    uiState: CreateSessionUiState,
    themes: List<BingoTheme>,
    isFormOk: Boolean,
    event: (event: CreateSessionEvent) -> Unit
) {
    val scrollState = rememberScrollState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .sizeIn(maxWidth = 500.dp, maxHeight = 1000.dp)
                .verticalScroll(scrollState)
        ) {

            val leadingIconModifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))

            val contentScale = ContentScale.Crop

            SessionNameComponent(
                uiState = uiState,
                onUpdateName = { event(CreateSessionEvent.OnUpdateName(it)) },
                leadingIconModifier = leadingIconModifier
            )

            SessionThemeSelector(
                uiState = uiState,
                themes = themes,
                contentScale = contentScale,
                onUpdateThemeId = { event(CreateSessionEvent.OnUpdateThemeId(it)) },
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
                leadingIconModifier = leadingIconModifier
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                enabled = isFormOk,
                onClick = { event(CreateSessionEvent.OnCreateNewSession) },
                modifier = Modifier
                    .width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.create_session_button))
            }
        }
    }
}

@PortraitPreviews
@Composable
fun Preview() {
    PortraitCreateSessionScreen(
        uiState = mockSessionUiState(),
        themes = emptyList(),
        isFormOk = true,
        event = { })
}