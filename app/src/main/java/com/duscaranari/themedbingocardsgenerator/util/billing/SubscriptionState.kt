package com.duscaranari.themedbingocardsgenerator.util.billing

import com.android.billingclient.api.ProductDetails

sealed class SubscriptionState() {
    object Loading : SubscriptionState()

    object Error : SubscriptionState()

    data class Checked(
        val subscribed: Boolean,
        val offerDetails: List<ProductDetails.SubscriptionOfferDetails>?
    ) : SubscriptionState()
}