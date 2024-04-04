package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.screens.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState
import com.duscaranari.themedbingocardsgenerator.ui.theme.getRandomLightColor

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
    Row(modifier = modifier) {
        var expanded by remember { mutableStateOf(false) }
        val selectedTheme = themes.find { it.id == uiState.themeId }
        val color by remember { mutableStateOf(getRandomLightColor()) }

        if (selectedTheme != null) {
            AsyncImage(
                model = selectedTheme.picture,
                contentDescription = stringResource(id = R.string.theme_picture),
                modifier = leadingIconModifier,
                contentScale = contentScale
            )
        } else {
            Box(modifier = leadingIconModifier) {
                Surface(
                    color = color,
                    modifier = Modifier
                        .fillMaxSize()
                ) {}

                Text(
                    text = "?",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(1f)
        ) {
            TextField(
                value = selectedTheme?.name ?: "",
                label = { Text(text = stringResource(id = R.string.theme_textField)) },
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
                                contentDescription = stringResource(id = R.string.theme_picture),
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