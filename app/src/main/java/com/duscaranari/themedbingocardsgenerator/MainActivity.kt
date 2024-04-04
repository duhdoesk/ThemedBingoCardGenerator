package com.duscaranari.themedbingocardsgenerator

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.WindowMetricsCalculator
import com.duscaranari.themedbingocardsgenerator.ui.app.ThemedBingoApp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.ui.theme.ThemedBingoCardsGeneratorTheme
import com.duscaranari.themedbingocardsgenerator.util.billing.SubscriptionState
import com.duscaranari.themedbingocardsgenerator.util.connectivity.ConnectivityObserver
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
        val width = metrics.bounds.width()
        val height = metrics.bounds.height()

        requestedOrientation =
            if (width > height) ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            else ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT

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
                        .collectAsState(initial = ConnectivityObserver.Status.Unavailable)

                    /**
                     * Subscription check
                     */
                    val subscribed = viewModel
                        .subs
                        .collectAsStateWithLifecycle()
                        .value

                    /**
                     * Sign in check
                     */
                    val googleUser = viewModel
                        .googleUser
                        .collectAsStateWithLifecycle()
                        .value

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                        onResult = { result ->
                            if(result.resultCode == RESULT_OK) {
                                lifecycleScope.launch {
                                    val signInResult = viewModel.authHelper.signInWithIntent(
                                        intent = result.data ?: return@launch
                                    )
                                    viewModel.onSignInResult(signInResult)
                                }
                            }
                        }
                    )

                    when (connectivity) {
                        ConnectivityObserver.Status.Available -> {
                            when (subscribed) {
                                is SubscriptionState.Checked -> {

                                    /**
                                     * App calling
                                     */
                                    ThemedBingoApp(
                                        billingHelper = viewModel.billingHelper,
                                        subscribed = subscribed.subscribed,
                                        offerDetails = subscribed.offerDetails,
                                        onBingoTypeChange = { viewModel.setBingoType(it) },
                                        activity = this,
                                        googleUser = googleUser,
                                        onSignIn = {
                                            lifecycleScope.launch {
                                                val signInIntentSender = viewModel.authHelper.signIn()
                                                launcher.launch(
                                                    IntentSenderRequest.Builder(signInIntentSender ?: return@launch)
                                                        .build()
                                                )
                                            }
                                        },
                                        onSignOut = { viewModel.onSignOut() }
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