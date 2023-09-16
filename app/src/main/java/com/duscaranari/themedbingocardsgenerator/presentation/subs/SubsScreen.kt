package com.duscaranari.themedbingocardsgenerator.presentation.subs

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubsScreen(subsViewModel: SubsViewModel = hiltViewModel()) {

    val activity = LocalContext.current as Activity
    val billingHelper = subsViewModel.billingClientSetup(activity)
    val productDetails = billingHelper.getProductsDetails()
    val currentSubscription = billingHelper.subscriptions.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(text = productDetails.toString())

        ListItem(
            headlineText = {
                Text(
                    text = "Drawer Access",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            leadingContent = {
                Text(text = if (currentSubscription.contains("drinks")) "Purchased" else "Not Purchased")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            TextButton(
                onClick = {
                    billingHelper.checkSubscriptionStatus("monthly-drawer-access")
                }
            ) {
                Text(text = "Monthly plan")
            }
            TextButton(
                onClick = {
                    billingHelper.checkSubscriptionStatus("trimestral-drawer-access")
                }
            ) {
                Text(text = "Trimester plan")
            }
            TextButton(
                onClick = {
                    billingHelper.checkSubscriptionStatus("annual-drawer-access")
                }
            ) {
                Text(text = "Annual plan")
            }
        }
    }
}

@Preview
@Composable
fun SubsScreenPreview() {
    SubsScreen()
}