package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun FinishDrawConfirmationDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(id = R.string.finish_draw_dialog_title)) },
        text = { Text(stringResource(id = R.string.finish_draw_dialog_body)) },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(stringResource(id = R.string.confirm).uppercase())
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(stringResource(id = R.string.cancel).uppercase())
            }
        },
    )
}