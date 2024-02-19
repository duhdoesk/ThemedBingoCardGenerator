package com.duscaranari.themedbingocardsgenerator.util.ads

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration

fun adsSetup(context: Context) {
    MobileAds.initialize(context)

    val req = RequestConfiguration
        .Builder()
        .setTestDeviceIds(
            listOf(
                "BFD15F0D985847E95433306355594EE5",
                "B76809184C69354B79EFE2122687CDA5"
            )
        )
        .build()

    MobileAds.setRequestConfiguration(req)
}