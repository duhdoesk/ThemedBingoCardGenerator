package com.duscaranari.themedbingocardsgenerator.ui.presentation.session.screens.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.session.event.SessionUiEvent

@Composable
fun ButtonsRow(
    event: (event: SessionUiEvent) -> Unit
) {
    Row {
        val buttonsModifier = Modifier.weight(1f)

        Button(
            onClick = { event(SessionUiEvent.OnFinishSession) },
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ),
            modifier = buttonsModifier
        ) {
            Text(text = stringResource(id = R.string.finish_draw))
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { event(SessionUiEvent.OnDrawNextCharacter) },
            modifier = buttonsModifier
        ) {
            Text(text = stringResource(id = R.string.draw_next_character))
        }
    }
}