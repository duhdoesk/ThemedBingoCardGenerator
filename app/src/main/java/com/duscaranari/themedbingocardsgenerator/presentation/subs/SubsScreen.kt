package com.duscaranari.themedbingocardsgenerator.presentation.subs

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun SubsScreen(subsViewModel: SubsViewModel = hiltViewModel()) {

    val billingHelper = subsViewModel.billingClientSetup(LocalContext.current as Activity)

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitSubsScreen(billingHelper)
        else -> LandscapeSubsScreen(billingHelper)
    }
}

@Composable
fun PortraitSubsScreen(billingHelper: BillingHelper?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier.sizeIn(
                maxWidth = 600.dp,
                maxHeight = 1000.dp
            )
        ) {

            Spacer(modifier = Modifier.weight(0.5f))

            Text(
                text = stringResource(id = R.string.plan),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.plan_desc),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            SubsPlanCards(
                onClick = { billingHelper?.checkSubscriptionStatus(it) }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }

}

@Composable
fun LandscapeSubsScreen(billingHelper: BillingHelper?) {

}

@PortraitPreviews
@Composable
fun PortraitSubsScreenPreview() {
    PortraitSubsScreen(null)
}