package com.duscaranari.themedbingocardsgenerator.ui.navigation

import android.app.Activity
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.ui.navigation.component.AppNavigationRail
import com.duscaranari.themedbingocardsgenerator.ui.navigation.component.BottomBar
import com.duscaranari.themedbingocardsgenerator.ui.navigation.component.TopBar
import com.duscaranari.themedbingocardsgenerator.ui.presentation.about.AboutScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.classic.ClassicCardScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.CardScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.characters.CharacterScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.CreateSessionScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.classic.ClassicDrawerScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed.DrawerScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.HomeScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.ui.presentation.profile.ProfileScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions.SessionsScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.sign_in.SignInScreen
import com.duscaranari.themedbingocardsgenerator.ui.presentation.subs.SubsScreen
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun AppNavigation(
    billingHelper: BillingHelper,
    subscribed: Boolean,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    onBingoTypeChange: (bingoType: BingoType) -> Unit,
    activity: Activity,
    googleUser: UserData?,
    onSignIn: () -> Unit,
    onSignOut: () -> Unit
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreens.entries.find {
        it.name == backStackEntry?.destination?.route?.substringBefore("/")
    } ?: AppScreens.Home
    val orientation = rememberDeviceOrientation()

    Scaffold(
        topBar = {
            if (orientation == DeviceOrientation.Portrait) {
                TopBar(
                    currentScreen = currentScreen,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    navigateToProfile = { navController.navigate(AppScreens.Profile.name) },
                    googleUser = googleUser)
            }
        },
        bottomBar = {
            if (!subscribed) {
                BottomBar()
            }
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier.padding(innerPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (orientation == DeviceOrientation.Landscape) {
                AppNavigationRail(
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    modifier = Modifier
                        .fillMaxHeight()
                )
            }

            val startDestination =
                if (googleUser == null) AppScreens.SignIn.name
                else AppScreens.Home.name

            NavHost(
                navController = navController,
                startDestination = startDestination,
                modifier = Modifier.weight(1f),
            ) {

                composable(AppScreens.About.name) {
                    AboutScreen()
                }

                composable(AppScreens.Card.name) {
                    CardScreen(navController = navController)
                }

                composable(AppScreens.Home.name) {
                    HomeScreen(
                        navController,
                        subscribed,
                        onBingoTypeChange = { onBingoTypeChange(it) }
                    )
                }

                composable("${AppScreens.Character.name}/{themeId}") {
                    CharacterScreen()
                }

                composable(AppScreens.Drawer.name) {
                    DrawerScreen(navController = navController)
                }

                composable(AppScreens.Subs.name) {
                    SubsScreen(
                        billingHelper,
                        offerDetails,
                        navController,
                        activity
                    )
                }

                composable(AppScreens.ClassicDrawer.name) {
                    ClassicDrawerScreen()
                }

                composable(AppScreens.ClassicCard.name) {
                    ClassicCardScreen()
                }

                composable(AppScreens.Sessions.name) {
                    SessionsScreen(
                        navController = navController,
                        googleUser = googleUser
                    )
                }

                composable(AppScreens.SignIn.name) {
                    SignInScreen(onSignIn = onSignIn)
                }

                composable(AppScreens.Profile.name) {
                    ProfileScreen(onSignOut = onSignOut)
                }

                composable(AppScreens.CreateSession.name) {
                    CreateSessionScreen(navController = navController)
                }
            }
        }
    }
}