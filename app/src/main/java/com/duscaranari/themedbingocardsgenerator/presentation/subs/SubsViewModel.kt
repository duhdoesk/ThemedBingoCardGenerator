package com.duscaranari.themedbingocardsgenerator.presentation.subs

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.duscaranari.themedbingocardsgenerator.util.PurchaseHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubsViewModel @Inject constructor(
    private val purchaseHelper: PurchaseHelper
) : ViewModel() {

    val buyEnabled = purchaseHelper.buyEnabled
    val consumeEnabled = purchaseHelper.consumeEnabled
    val productName = purchaseHelper.productName
    val statusText = purchaseHelper.statusText

    fun makePurchase() {
        purchaseHelper.makePurchase()
    }
}
