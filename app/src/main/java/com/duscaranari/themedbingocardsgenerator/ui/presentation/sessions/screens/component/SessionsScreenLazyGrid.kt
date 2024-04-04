package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme

@Composable
fun SessionsScreenLazyGrid(
    sessions: List<SessionDTO>,
    themes: List<BingoTheme>,
    modifier: Modifier = Modifier,
    columns: GridCells = GridCells.Fixed(1),
    onJoinSession: (session: SessionDTO) -> Unit
) {

    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = columns,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            for (session in sessions) {
                themes.find { it.id == session.themeId }?.let { theme ->
                    item {
                        SessionsScreenCard(
                            session = session,
                            theme = theme,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .fillMaxWidth(),
                            onJoinSession = { onJoinSession(it) })
                    }
                }
            }
        }
    }
}