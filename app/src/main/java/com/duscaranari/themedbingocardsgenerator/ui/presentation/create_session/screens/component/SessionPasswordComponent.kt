package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.getRandomLightColor

@Composable
fun SessionPasswordComponent(
    modifier: Modifier = Modifier,
    uiState: CreateSessionUiState,
    leadingIconModifier: Modifier,
    onUpdateLockedState: (locked: Boolean) -> Unit,
    onUpdatePassword: (password: String) -> Unit
) {
    Column(modifier = modifier) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = uiState.locked,
                onCheckedChange = { onUpdateLockedState(it) })
            Text(text = stringResource(id = R.string.private_session_checkbox))
        }

        Row {

            val color by remember { mutableStateOf(getRandomLightColor()) }

            Box(modifier = leadingIconModifier) {
                Surface(
                    color = color,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val icon = if (uiState.locked) R.drawable.baseline_lock_24 else R.drawable.baseline_lock_open_24

                    Icon(
                        painter = painterResource(id = icon),
                        tint = Color.Black,
                        contentDescription = stringResource(id = R.string.key_icon),
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                TextField(
                    value = uiState.password,
                    enabled = uiState.locked,
                    label = { Text(text = stringResource(id = R.string.password_textField)) },
                    onValueChange = { onUpdatePassword(it) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                for (error in uiState.passwordErrors) {
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}