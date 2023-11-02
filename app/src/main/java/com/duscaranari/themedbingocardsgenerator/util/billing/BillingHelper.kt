package com.duscaranari.themedbingocardsgenerator.util.billing

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.BillingResponseCode
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.queryProductDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val BILLING_TAG = "Billing Helper"
const val PRODUCT_ID = "drawer_access"

class BillingHelper(private val activity: Activity) {

    /**
     * Stateflow to contain billing client connection state
     */
    private val _billingClientConnectionState =
        MutableStateFlow<BillingClientConnectionState>(BillingClientConnectionState.Disconnected)
    val billingClientConnectionState = _billingClientConnectionState.asStateFlow()


    /**
     * Purchases Listener
     */
    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingResponseCode.OK && purchases != null) {
                for (purchase in purchases) {
                    GlobalScope.launch {
                        handlePurchase(purchase)
                    }
                }
            } else if (billingResult.responseCode == BillingResponseCode.USER_CANCELED) {
                // Handle an error caused by a user cancelling the purchase flow.
                Log.d(
                    BILLING_TAG,
                    "Billing Result - purchasesUpdatedListener - Code ${billingResult.responseCode}. ${billingResult.debugMessage}"
                )
            } else {
                // Handle any other error codes.
                Log.d(
                    BILLING_TAG,
                    "Billing Result - purchasesUpdatedListener - Code ${billingResult.responseCode}. ${billingResult.debugMessage}"
                )
            }
        }


    /**
     * Billing Client
     */
    private var billingClient = BillingClient.newBuilder(activity)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    /**
     * Setup Billing Client
     */
    fun billingClientSetup() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.
                    _billingClientConnectionState.value = BillingClientConnectionState.Connected
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                _billingClientConnectionState.value = BillingClientConnectionState.Disconnected
            }
        })
    }


    /**
     * Query for available products
     */
    private suspend fun getProductList(): List<ProductDetails>? {
        val productList = ArrayList<QueryProductDetailsParams.Product>()
        productList.add(
            QueryProductDetailsParams.Product.newBuilder()
                .setProductId(PRODUCT_ID)
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
        return productDetailsResult.productDetailsList
    }


    /**
     * Query for purchases
     */
    private fun queryPurchases(): List<Purchase> {
        var purchasesList: List<Purchase> = emptyList()

        val params = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.SUBS)

        // uses queryPurchasesAsync Kotlin extension function
        billingClient.queryPurchasesAsync(params.build()) { result, purchases ->
            if (result.responseCode == BillingResponseCode.OK) {
                purchasesList = purchases
            }
        }

        return purchasesList
    }


    /**
     * Start Billing FLow for a purchase process
     */
    fun startBillingFlow(
        productDetails: ProductDetails,
        selectedOfferToken: String
    ) {
        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                // retrieve a value for "productDetails" by calling queryProductDetailsAsync()
                .setProductDetails(productDetails)
                // to get an offer token, call ProductDetails.subscriptionOfferDetails()
                // for a list of offers that are available to the user
                .setOfferToken(selectedOfferToken)
                .build()
        )

        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()

        // Launch the billing flow
        val billingResult = billingClient.launchBillingFlow(activity, billingFlowParams)

        Log.d(
            BILLING_TAG,
            "Billing Result - startBillingFlow - Code ${billingResult.responseCode}. ${billingResult.debugMessage}"
        )
    }

    /**
     * Handle Subscriptions
     */
    private fun handlePurchase(purchase: Purchase) {
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
                    Log.d(
                        BILLING_TAG,
                        "Billing Result - Acknowledge Purchase - Code ${billingResult.responseCode}. ${billingResult.debugMessage}"
                    )
                }
            }
        }
    }


    /**
     * Check if user is subscribed
     */
    fun checkSubscription(): Boolean {
        for (purchase in queryPurchases()) {
            if (purchase.products.contains(PRODUCT_ID)) {
                return true
            }
        }

        return false
    }


    /**
     * Check for pending purchases to be acknowledged
     */
    fun checkForPendingPurchases() {
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