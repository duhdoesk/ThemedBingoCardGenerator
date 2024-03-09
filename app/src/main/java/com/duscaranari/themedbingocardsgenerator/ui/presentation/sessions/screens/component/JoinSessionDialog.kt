package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.screens.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session

@Composable
fun JoinSessionDialog(
    session: Session,
    onDismiss: () -> Unit,
    onJoinSession: (password: String?) -> Unit,
    modifier: Modifier = Modifier
) {
    val password: MutableState<String?> = remember { mutableStateOf(null) }

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier = modifier) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = session.name.uppercase(),
                    fontWeight = FontWeight.Bold
                )

                if (session.locked) {
                    Text(text = stringResource(R.string.insert_the_password))

                    TextField(
                        value = password.value ?: "",
                        onValueChange = { password.value = it },
                        label = { Text(text = "password") },
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                } else {
                    Text(
                        text = stringResource(R.string.tap_confirm),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(stringResource(id = R.string.cancel))
                    }

                    Button(
                        onClick = { onJoinSession(password.value) }
                    ) {
                        Text(stringResource(id = R.string.confirm))
                    }
                }
            }
        }
    }
}