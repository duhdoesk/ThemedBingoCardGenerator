package com.duscaranari.themedbingocardsgenerator.ui.app

import android.app.Activity
import androidx.compose.runtime.Composable
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppNavigation
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper

@Composable
fun ThemedBingoApp(
    billingHelper: BillingHelper,
    authHelper: AuthHelper,
    subscribed: Boolean,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    onBingoTypeChange: (bingoType: BingoType) -> Unit,
    activity: Activity,
    googleUser: UserData?,
    onSignIn: () -> Unit,
    onSignOut: () -> Unit
) {
    AppNavigation(
        billingHelper = billingHelper,
        authHelper = authHelper,
        subscribed = subscribed,
        offerDetails = offerDetails,
        onBingoTypeChange = { onBingoTypeChange(it) },
        activity = activity,
        googleUser = googleUser,
        onSignIn = onSignIn,
        onSignOut = onSignOut
    )
}