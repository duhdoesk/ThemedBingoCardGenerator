package com.duscaranari.themedbingocardsgenerator.presentation.subs

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.ProductDetailsResult
import com.duscaranari.themedbingocardsgenerator.util.BillingHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubsViewModel @Inject constructor() : ViewModel() {

    fun billingClientSetup(activity: Activity): BillingHelper {
        val billingHelper = BillingHelper(activity)
        billingHelper.billingSetup()
        billingHelper.hasSubscription()

        return billingHelper
    }
}