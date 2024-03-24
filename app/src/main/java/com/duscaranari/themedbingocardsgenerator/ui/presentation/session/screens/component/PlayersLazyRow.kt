package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User

@Composable
fun PlayersLazyRow(
    modifier: Modifier = Modifier,
    pictureSize: Dp = 60.dp,
    players: List<User>
) {
    Box(modifier = modifier) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                space = 12.dp,
                alignment = Alignment.Start
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(players) { player ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = player.picture,
                        contentDescription = "Player Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(pictureSize)
                            .clip(CircleShape))

                    Text(
                        text = player.name.split(" ").first(),
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}