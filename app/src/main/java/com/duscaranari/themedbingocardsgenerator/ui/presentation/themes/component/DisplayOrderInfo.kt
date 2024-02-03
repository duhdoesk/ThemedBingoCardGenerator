package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.state.ThemesDisplayOrder

@Composable
fun DisplayOrderInfo(
    displayOrder: ThemesDisplayOrder,
    onDisplayOrderChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier) {

        OutlinedButton(
            onClick = { onDisplayOrderChange() },
            modifier = Modifier.width(200.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sorting_by).uppercase(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = " ${displayOrder.name}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

            Icon(
                painter = painterResource(id = R.drawable.baseline_change_circle_24),
                contentDescription = "Change Icon",
                modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Preview
@Composable
fun DisplayOrderInfoPreview() {
    DisplayOrderInfo(
        displayOrder = ThemesDisplayOrder.NAME,
        onDisplayOrderChange = {  }
    )
}