package com.duscaranari.themedbingocardsgenerator.ui.presentation.subs

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.billing.SubscriptionState
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun SubsScreen(
    billingHelper: BillingHelper,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    activity: Activity
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (rememberDeviceOrientation()) {
            is DeviceOrientation.Portrait -> PortraitSubsScreen(
                billingHelper,
                offerDetails,
                activity
            )

            else -> LandscapeSubsScreen(billingHelper, offerDetails, activity)
        }
    }
}

@Composable
fun PortraitSubsScreen(
    billingHelper: BillingHelper,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    activity: Activity
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

        SubsPlanCardsColumn(
            onClick = { billingHelper.querySubscriptionPlans(it, activity) },
            offerDetails = offerDetails
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun LandscapeSubsScreen(
    billingHelper: BillingHelper,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    activity: Activity
) {

    Row(
        modifier = Modifier.sizeIn(
            maxWidth = 1000.dp,
            maxHeight = 600.dp
        )
    ) {

        Column(modifier = Modifier.weight(0.3f)) {
            Text(
                text = stringResource(id = R.string.plan),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.plan_desc),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Column(Modifier.weight(0.7f)) {
            SubsPlanCardsColumn(
                onClick = { billingHelper.querySubscriptionPlans(it, activity) },
                offerDetails = offerDetails
            )
        }
    }
}