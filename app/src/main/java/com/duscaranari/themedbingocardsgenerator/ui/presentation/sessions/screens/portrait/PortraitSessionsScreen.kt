package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.portrait

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.session.model.mockSession
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.mockBingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component.SessionsScreenLazyGrid
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews

@Composable
fun PortraitSessionsScreen(
    sessions: List<Session>,
    themes: List<BingoTheme>,
    onPickASession: (session: Session) -> Unit,
    onNavigateToCreateASession: () -> Unit
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(maxWidth = 500.dp, maxHeight = 800.dp)
        ) {

            Text(
                text = stringResource(R.string.tap_in_a_session_to_join_it),
                modifier = Modifier.fillMaxWidth()
            )

            SessionsScreenLazyGrid(
                sessions = sessions,
                themes = themes,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .weight(1f),
                onJoinSession = { session ->
                    onPickASession(session)
                })

            Button(onClick = onNavigateToCreateASession) {
                Text(text = stringResource(id = R.string.create_session))
            }
        }
    }
}

@PortraitPreviews
@Composable
fun Preview() {
    PortraitSessionsScreen(
        sessions = listOf(mockSession()),
        themes = listOf(mockBingoTheme()),
        onPickASession = { },
        onNavigateToCreateASession = { }
    )
}