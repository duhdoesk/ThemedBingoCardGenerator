package com.duscaranari.themedbingocardsgenerator.util

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.BillingResponseCode
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.ProductDetails.SubscriptionOfferDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryProductDetails
import com.duscaranari.themedbingocardsgenerator.TAG
import com.google.common.collect.ImmutableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SUBS_ID = "drawer_access"

sealed class Subscription() {
    object Loading : Subscription()

    object Error : Subscription()

    data class Checked(
        val subscribed: Boolean,
        val offerDetails: List<SubscriptionOfferDetails>?
    ) : Subscription()
}

class BillingHelper(private val activity: Activity) {

    private val _subscription = MutableStateFlow<Subscription>(Subscription.Loading)
    val subscribed = _subscription.asStateFlow()

    private val purchaseUpdateListener = PurchasesUpdatedListener { result, purchases ->
        if (result.responseCode == BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (result.responseCode == BillingResponseCode.USER_CANCELED) {
            // User canceled the purchase
            Log.d(TAG, "User canceled the purchase")
        } else {
            // Handle other error cases
            Log.d(TAG, result.debugMessage)
        }
    }

    private var billingClient: BillingClient = BillingClient.newBuilder(activity)
        .setListener(purchaseUpdateListener)
        .enablePendingPurchases()
        .build()

    private fun handlePurchase(purchase: Purchase) {

        Log.d("Purchase", "Iniciando fluxo.")
        Log.d("Purchase State", purchase.purchaseState.toString())
        Log.d("Purchase Acknowledged", purchase.isAcknowledged.toString())

        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        val listener = ConsumeResponseListener { _, _ -> }

        billingClient.consumeAsync(consumeParams, listener)

        if (!purchase.isAcknowledged) {

            Log.d("Purchase", "Iniciando fluxo nao reconhecido.")
            Log.d("Purchase State", purchase.purchaseState.toString())
            Log.d("Purchase Acknowledged", purchase.isAcknowledged.toString())

            val acknowledgePurchaseParams = AcknowledgePurchaseParams
                .newBuilder()
                .setPurchaseToken(purchase.purchaseToken)
                .build()

            billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
                if (billingResult.responseCode == BillingResponseCode.OK) {
                    _subscription.value = Subscription.Checked(true, null)
                }

                Log.d("Purchase", "Terminando fluxo - dentro do metodo acknowledge.")
                Log.d("Purchase State", purchase.purchaseState.toString())
                Log.d("Purchase Acknowledged", purchase.isAcknowledged.toString())
            }
        }
    }

    fun billingSetup() {
        Log.d(TAG, "billingSetup called")
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if (result.responseCode == BillingResponseCode.OK) {
                    Log.d(TAG, "billingSetup success")
                    isSubscribed()
                } else {
                    Log.d(TAG, result.debugMessage)
                    _subscription.value = Subscription.Error
                }
            }

            override fun onBillingServiceDisconnected() {
                // Handle billing service disconnection
                _subscription.value = Subscription.Error
            }
        })
    }

    private fun isSubscribed() {

        val queryPurchaseParams = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)
            .build()

        billingClient.queryPurchasesAsync(
            queryPurchaseParams
        ) { result, purchases ->
            when (result.responseCode) {
                BillingResponseCode.OK -> {
                    for (purchase in purchases) {
                        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED &&
                            purchase.products.contains(SUBS_ID)
                        ) {
                            Log.d(TAG, "purchase success")
                            _subscription.value = Subscription.Checked(true, null)
                            return@queryPurchasesAsync
                        }
                    }
                }

                BillingResponseCode.USER_CANCELED -> {
                    // User canceled the purchase
                    Log.d(TAG, result.debugMessage)
                }

                else -> {
                    // Handle other error cases
                    Log.d(TAG, result.debugMessage)
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                _subscription.value = Subscription.Checked(false, getSubsOfferDetails())
            }

        }
    }

    fun querySubscriptionPlans(
        subscriptionPlanId: String,
    ) {
        val queryProductDetailsParams =
            QueryProductDetailsParams.newBuilder()
                .setProductList(
                    ImmutableList.of(
                        QueryProductDetailsParams.Product.newBuilder()
                            .setProductId(SUBS_ID)
                            .setProductType(BillingClient.ProductType.SUBS)
                            .build(),
                    )
                )
                .build()

        billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult, productDetailsList ->
            if (billingResult.responseCode == BillingResponseCode.OK) {
                var offerToken = ""
                val productDetails = productDetailsList.firstOrNull { productDetails ->
                    productDetails.subscriptionOfferDetails?.any {
                        if (it.basePlanId == subscriptionPlanId) {
                            offerToken = it.offerToken
                            true
                        } else {
                            false
                        }
                    } == true
                }
                productDetails?.let {
                    val productDetailsParamsList = listOf(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                            .setProductDetails(it)
                            .setOfferToken(offerToken)
                            .build()
                    )

                    val billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .build()

                    billingClient.launchBillingFlow(activity, billingFlowParams)
                }
            }
        }
    }

    private suspend fun getSubsOfferDetails(): List<ProductDetails.SubscriptionOfferDetails>? {
        val productList = ArrayList<QueryProductDetailsParams.Product>()

        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(SUBS_ID)
                .setProductType(BillingClient.ProductType.SUBS)
                .build()
        )

        val params = QueryProductDetailsParams.newBuilder()
        params.setProductList(productList)

        // leverage queryProductDetails Kotlin extension function
        val productDetailsResult = withContext(Dispatchers.IO) {
            billingClient.queryProductDetails(params.build())
        }

        // Process the result.
        with(productDetailsResult.productDetailsList) {
            if (!this.isNullOrEmpty()) {
                return productDetailsResult.productDetailsList?.first()?.subscriptionOfferDetails
            } else {
                return emptyList()
            }
        }
    }

    fun queryPurchases() {
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)

        // uses queryPurchasesAsync Kotlin extension function
        billingClient.queryPurchasesAsync(params.build()) { result, purchases ->
            when (result.responseCode) {
                BillingResponseCode.OK -> {
                    for (purchase in purchases) {
                        handlePurchase(purchase)
                    }
                }
            }
        }
    }
}
