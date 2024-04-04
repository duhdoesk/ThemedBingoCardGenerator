package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.mockSession
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.mockBingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.getRandomLightColor

@Composable
fun SessionsScreenCard(
    session: SessionDTO,
    theme: BingoTheme,
    modifier: Modifier = Modifier,
    onJoinSession: (session: SessionDTO) -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.clickable { onJoinSession(session) }
    ) {

        val color by remember { mutableStateOf(getRandomLightColor()) }

        Surface(color = color) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                AsyncImage(
                    model = theme.picture,
                    contentDescription = stringResource(id = R.string.theme_picture),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(68.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = session.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )

                    Text(
                        text = theme.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black
                    )
                }

                if (session.locked) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_lock_24),
                        contentDescription = stringResource(id = R.string.lock_icon),
                        tint = Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(28.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    SessionsScreenCard(
        session = mockSession(),
        theme = mockBingoTheme(),
        modifier = Modifier
            .width(400.dp),
        onJoinSession = {  }
    )
}