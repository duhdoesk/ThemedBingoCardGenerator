package com.duscaranari.themedbingocardsgenerator.presentation.subs

import android.app.Activity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SubsScreen(subsViewModel: SubsViewModel = hiltViewModel()) {

    Button(
        onClick = { subsViewModel.makePurchase() },
        Modifier.padding(20.dp),
        enabled = subsViewModel.buyEnabled.collectAsState().value
    ) {
        Text("Purchase")
    }
}

@Preview
@Composable
fun SubsScreenPreview() {
    SubsScreen()
}