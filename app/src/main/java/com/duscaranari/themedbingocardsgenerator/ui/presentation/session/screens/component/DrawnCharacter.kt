package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter

@Composable
fun DrawnCharacter(
    listOfCharacters: List<BingoCharacter>,
    modifier: Modifier = Modifier,
    maxPictureSize: Dp = 240.dp
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            alignment = Alignment.CenterVertically,
            space = 8.dp
        )
    ) {
        val pictureModifier = Modifier
            .widthIn(max = maxPictureSize)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))

        listOfCharacters.lastOrNull()?.let {
            AsyncImage(
                model = it.picture,
                contentDescription = "Character Picture",
                contentScale = ContentScale.Crop,
                modifier = pictureModifier
            )
        } ?: Image(
            painter = painterResource(id = R.drawable.hot_water_logo),
            contentDescription = "Company Logo",
            contentScale = ContentScale.Crop,
            modifier = pictureModifier
        )

        Text(text = listOfCharacters.lastOrNull()?.name ?: "No character was drawn yet")
    }
}