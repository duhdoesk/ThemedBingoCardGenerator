package com.duscaranari.themedbingocardsgenerator.ui.presentation.themes.component

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ThemesScreenSearchBar(
    query: String,
    onQuery: (name: String) -> Unit,
    modifier: Modifier = Modifier
) {

    TextField(
        placeholder = { Text(text = "Search") },
        value = query,
        onValueChange = { onQuery(it) },
        modifier = modifier
    )
}