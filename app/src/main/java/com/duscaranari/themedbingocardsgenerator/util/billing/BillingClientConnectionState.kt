package com.duscaranari.themedbingocardsgenerator.util.billing

sealed class BillingClientConnectionState {
    object Connected: BillingClientConnectionState()
    object Disconnected: BillingClientConnectionState()
}
