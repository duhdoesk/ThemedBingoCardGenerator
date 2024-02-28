package com.duscaranari.themedbingocardsgenerator

import android.app.Activity
import android.app.appsearch.AppSearchResult
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.ui.navigation.AppNavigation
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedBingoCardsGeneratorTheme
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.billing.SubscriptionState
import com.duscaranari.themedbingocardsgenerator.util.connectivity.ConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val TAG = "BILLING"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            ThemedBingoCardsGeneratorTheme(
                bingoType = viewModel.bingoType.collectAsState().value
            ) {

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    /**
                     * Connectivity check
                     */
                    val connectivity by viewModel
                        .networkConnectivityObserver
                        .observe()
                        .collectAsState(
                            initial = ConnectivityObserver.Status.Unavailable
                        )

                    /**
                     * Subscription check
                     */
                    val subscribed = viewModel
                        .subs
                        .collectAsState()
                        .value

                    when (connectivity) {
                        ConnectivityObserver.Status.Available -> {
                            when (subscribed) {
                                is SubscriptionState.Checked -> {

                                    /**
                                     * App calling
                                     */
                                    ThemedBingoApp(
                                        billingHelper = viewModel.billingHelper,
                                        authHelper = viewModel.authHelper,
                                        subscribed = subscribed.subscribed,
                                        offerDetails = subscribed.offerDetails,
                                        onBingoTypeChange = { viewModel.setBingoType(it) },
                                        activity = this
                                    )
                                }

                                is SubscriptionState.Error -> {
                                    ErrorScreen(
                                        errorMessage = R.string.billing_error,
                                        onTryAgain = { viewModel.billingHelper.billingSetup() }
                                    )
                                }

                                else -> {
                                    LoadingScreen()
                                }
                            }
                        }

                        else -> {
                            ErrorScreen(
                                errorMessage = R.string.no_internet_connection,
                                onTryAgain = { }
                            )
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
    authHelper: AuthHelper,
    subscribed: Boolean,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    onBingoTypeChange: (bingoType: BingoType) -> Unit,
    activity: Activity
) {
    AppNavigation(
        billingHelper = billingHelper,
        authHelper = authHelper,
        subscribed = subscribed,
        offerDetails = offerDetails,
        onBingoTypeChange = { onBingoTypeChange(it) },
        activity = activity
    )
}