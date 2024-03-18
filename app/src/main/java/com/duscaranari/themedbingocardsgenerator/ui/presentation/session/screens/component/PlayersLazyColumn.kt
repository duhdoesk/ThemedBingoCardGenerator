package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User

@Composable
fun PlayersLazyColumn(
    modifier: Modifier = Modifier,
    columns: GridCells = GridCells.Fixed(2),
    pictureSize: Dp = 40.dp,
    players: List<User>
) {
    Box(modifier = modifier) {
        LazyVerticalGrid(
            columns = columns,
            modifier = Modifier.fillMaxSize()
        ) {
            items(players) { player ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = player.picture,
                        contentDescription = "Player picture.",
                        modifier = Modifier
                            .size(pictureSize))

                    Text(
                        text = player.name,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}