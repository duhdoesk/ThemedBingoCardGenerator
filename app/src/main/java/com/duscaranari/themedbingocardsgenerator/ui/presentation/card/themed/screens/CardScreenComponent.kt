@file:OptIn(ExperimentalMaterial3Api::class)

package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize

@Composable
fun SizeSelectorSwitchButton(
    optionSelected: CardSize,
    onClick: (Boolean) -> Unit
) {
    val checkedState = remember { mutableStateOf(optionSelected == CardSize.LARGE) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = R.string.card_size))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.medium_card)
            )

            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = !checkedState.value
                    onClick(checkedState.value)
                })

            Text(
                text = stringResource(id = R.string.large_card)
            )
        }
    }
}