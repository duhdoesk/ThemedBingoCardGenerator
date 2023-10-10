package com.duscaranari.themedbingocardsgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.navigation.AppNavigation
import com.duscaranari.themedbingocardsgenerator.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedBingoCardsGeneratorTheme
import com.duscaranari.themedbingocardsgenerator.util.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.Subscription
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "BILLING"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Ads Config
         */
        MobileAds.initialize(this)

        val req = RequestConfiguration
            .Builder()
            .setTestDeviceIds(listOf("BFD15F0D985847E95433306355594EE5"))
            .build()

        MobileAds.setRequestConfiguration(req)

        /**
         * Composable setup
         */
        setContent {
            ThemedBingoCardsGeneratorTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    /**
                     * Billing config ++ subscription check
                     */
                    val billingHelper = remember { BillingHelper(this) }
                    val subscribed = billingHelper.subscribed.collectAsState().value

                    LaunchedEffect(key1 = true) {
                        billingHelper.billingSetup()
                    }

                    when (subscribed) {
                        is Subscription.Checked -> {
                            if (!subscribed.subscribed) {
                                showInterstitialAd(this)
                            }

                            /**
                             * App calling
                             */
                            ThemedBingoApp(
                                billingHelper = billingHelper,
                                subscribed = subscribed.subscribed,
                                offerDetails = subscribed.offerDetails
                            )
                        }
                        is Subscription.Error -> {
                            ErrorScreen(
                                errorMessage = R.string.billing_error,
                                onTryAgain = { billingHelper.billingSetup() }
                            )
                        }
                        else -> {
                            LoadingScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemedBingoApp(
    billingHelper: BillingHelper,
    subscribed: Boolean,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?
) {
    AppNavigation(
        billingHelper,
        subscribed,
        offerDetails
    )
}