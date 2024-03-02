package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState
import kotlin.random.Random

@Composable
fun SessionNameComponent(
    modifier: Modifier = Modifier,
    uiState: CreateSessionUiState,
    leadingIconModifier: Modifier,
    onUpdateName: (name: String) -> Unit
) {
    Row(modifier = modifier) {

        val color by remember {
            mutableStateOf(
                Color(
                    blue = Random.nextInt(160, 256),
                    red = Random.nextInt(160, 256),
                    green = Random.nextInt(160, 256),
                    alpha = 255
                )
            )
        }

        Box(modifier = leadingIconModifier) {
            Surface(
                color = color,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_abc_24),
                    tint = Color.Black,
                    contentDescription = "ABC",
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            TextField(
                value = uiState.name,
                label = { Text(text = "name") },
                onValueChange = { onUpdateName(it) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

            for (error in uiState.nameErrors) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}