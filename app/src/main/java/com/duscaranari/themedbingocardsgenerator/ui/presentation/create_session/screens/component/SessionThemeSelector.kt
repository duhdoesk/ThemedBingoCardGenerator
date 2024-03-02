package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionThemeSelector(
    modifier: Modifier = Modifier,
    uiState: CreateSessionUiState,
    themes: List<BingoTheme>,
    leadingIconModifier: Modifier,
    contentScale: ContentScale,
    onUpdateThemeId: (themeId: String) -> Unit
) {
    Row {
        var expanded by remember { mutableStateOf(false) }
        val selectedTheme = themes.find { it.id == uiState.themeId }

        if (selectedTheme != null) {
            AsyncImage(
                model = selectedTheme.picture,
                contentDescription = "Theme picture",
                modifier = leadingIconModifier,
                contentScale = contentScale
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.hot_water_logo),
                contentDescription = "Logo",
                modifier = leadingIconModifier,
                contentScale = contentScale
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {
            TextField(
                value = selectedTheme?.name ?: "",
                label = { Text(text = "theme") },
                readOnly = true,
                onValueChange = { },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                themes.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.name) },
                        leadingIcon = {
                            AsyncImage(
                                model = item.picture,
                                contentDescription = "Theme picture",
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                        },
                        onClick = {
                            onUpdateThemeId(item.id)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}