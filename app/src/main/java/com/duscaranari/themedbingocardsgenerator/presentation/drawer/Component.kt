package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun DrawerButtons(
    isFinished: Boolean,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit
) {

    when (isFinished) {
        true -> {
            Button(
                onClick = onStartNewDraw,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.start_new_draw))
            }
        }

        false -> {
            Button(
                onClick = onDrawNextCharacter,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.draw_next_character))
            }

            TextButton(
                onClick = onFinishDraw,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = stringResource(id = R.string.finish_draw))
            }
        }
    }


}