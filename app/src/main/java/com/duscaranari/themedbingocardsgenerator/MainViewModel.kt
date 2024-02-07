package com.duscaranari.themedbingocardsgenerator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.data.local.di.BaseApplication
import com.duscaranari.themedbingocardsgenerator.data.network.util.DataUpdate
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.connectivity.NetworkConnectivityObserver
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val baseApplication: BaseApplication,
    val billingHelper: BillingHelper,
    dataUpdate: DataUpdate
) : ViewModel() {

    val subs = billingHelper.subscribed

    val connectivityObserver = NetworkConnectivityObserver(baseApplication)

    private val _bingoType = MutableStateFlow(BingoType.ONLINE)
    val bingoType = _bingoType.asStateFlow()

    init {
        viewModelScope.launch {
            billingSetup()
            adsSetup()
            dataUpdate.checkForUpdates()
        }
    }

    private fun billingSetup() {
        billingHelper.billingSetup()
        billingHelper.queryPurchases()
    }

    private fun adsSetup() {
        MobileAds.initialize(baseApplication)

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

    fun setBingoType(bingoType: BingoType) {
        _bingoType.update { bingoType }
    }
}