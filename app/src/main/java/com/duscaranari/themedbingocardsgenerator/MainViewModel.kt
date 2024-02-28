package com.duscaranari.themedbingocardsgenerator

import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.data.local.di.BaseApplication
import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.util.DataUpdate
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sign_in.state.SignInState
import com.duscaranari.themedbingocardsgenerator.util.ads.adsSetup
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import com.duscaranari.themedbingocardsgenerator.util.auth.SignInResult
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.connectivity.NetworkConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val baseApplication: BaseApplication,
    val billingHelper: BillingHelper,
    val networkConnectivityObserver: NetworkConnectivityObserver,
    val authHelper: AuthHelper,
    dataUpdate: DataUpdate
) : ViewModel() {

    val subs = billingHelper.subscribed

    private val _bingoType = MutableStateFlow(BingoType.ONLINE)
    val bingoType = _bingoType.asStateFlow()

    init {
        viewModelScope.launch {
            billingSetup()
            adsSetup(baseApplication)
            dataUpdate.checkForUpdates()
        }
    }

    private fun billingSetup() {
        billingHelper.billingSetup()
        billingHelper.queryPurchases()
    }

    fun setBingoType(bingoType: BingoType) {
        _bingoType.update { bingoType }
    }
}