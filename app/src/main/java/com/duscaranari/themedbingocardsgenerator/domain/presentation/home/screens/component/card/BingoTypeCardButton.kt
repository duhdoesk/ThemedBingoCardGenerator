package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.card

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType

@Composable
fun BingoTypeCardButton(
    onNavigate: () -> Unit,
    bingoType: BingoType,
    modifier: Modifier = Modifier,
    isSubscribed: Boolean = false,
    isPremium: Boolean = false,
    text: String,
    containerColor: Color,
    contentColor: Color
) {

    Button(
        onClick = { onNavigate() },
        enabled = bingoType.enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
    ) {

        if (isPremium && !isSubscribed) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock",
                modifier = Modifier.padding(end = 6.dp)
            )
        }

        Text(text = text)
    }
}