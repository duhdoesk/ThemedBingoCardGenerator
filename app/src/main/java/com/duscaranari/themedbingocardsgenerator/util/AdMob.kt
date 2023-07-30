package com.duscaranari.themedbingocardsgenerator.util

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

/*

TEST ADS IDS
BANNER: adUnitId = "ca-app-pub-3940256099942544/6300978111"
INTERSTITIAL: "ca-app-pub-3940256099942544/1033173712"

 */

@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),

        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = "ca-app-pub-8989134422683893/9629649525"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

fun showInterstitialAd(context: Context) {
    InterstitialAd.load(
        context,
        "ca-app-pub-8989134422683893/5668291581",
        AdRequest.Builder().build(),

        object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                interstitialAd.show(context as Activity)
            }
        }
    )
}