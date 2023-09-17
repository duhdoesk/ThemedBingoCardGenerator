package com.duscaranari.themedbingocardsgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.duscaranari.themedbingocardsgenerator.navigation.AppNavigation
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedBingoCardsGeneratorTheme
import com.duscaranari.themedbingocardsgenerator.util.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobileAds.initialize(this)

        val billingHelper = BillingHelper(this)
        billingHelper.billingSetup()

        val currentSubscription = billingHelper.subscriptions.value
        val subscribed = currentSubscription.contains("drawer_access")

        if (!subscribed) {
            showInterstitialAd(this)
        }

        setContent {
            ThemedBingoCardsGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(
                        billingHelper,
                        subscribed
                    )
                }
            }
        }
    }
}

@Composable
fun App(billingHelper: BillingHelper, subscribed: Boolean) {
    AppNavigation(
        billingHelper,
        subscribed
    )
}