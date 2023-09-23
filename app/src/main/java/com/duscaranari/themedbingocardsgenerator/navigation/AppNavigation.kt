package com.duscaranari.themedbingocardsgenerator.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.presentation.about.AboutScreen
import com.duscaranari.themedbingocardsgenerator.presentation.card.CardScreen
import com.duscaranari.themedbingocardsgenerator.presentation.characters.CharacterScreen
import com.duscaranari.themedbingocardsgenerator.presentation.drawer.DrawerScreen
import com.duscaranari.themedbingocardsgenerator.presentation.home.HomeScreen
import com.duscaranari.themedbingocardsgenerator.presentation.subs.SubsScreen
import com.duscaranari.themedbingocardsgenerator.presentation.themes.ThemesScreen
import com.duscaranari.themedbingocardsgenerator.util.BillingHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    billingHelper: BillingHelper,
    subscribed: Boolean,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreens.values().find {
        it.name == backStackEntry?.destination?.route?.substringBefore("/")
    } ?: AppScreens.Home

    Scaffold(
        topBar = {
            ThemedBingoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreens.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(AppScreens.About.name) {
                AboutScreen()
            }

            composable("${AppScreens.Card.name}/{themeId}") {
                CardScreen(navController)
            }

            composable(AppScreens.Home.name) {
                HomeScreen(
                    navController,
                    subscribed
                )
            }

            composable("${AppScreens.Themes.name}/{screen}") {
                ThemesScreen(navController)
            }

            composable("${AppScreens.Character.name}/{themeId}") {
                CharacterScreen()
            }

            composable(AppScreens.Drawer.name){
                DrawerScreen(navController)
            }

            composable(AppScreens.Subs.name) {
                SubsScreen(
                    billingHelper,
                    offerDetails
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemedBingoAppBar(
    currentScreen: AppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(stringResource(id = currentScreen.stringResource))
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Button"
                    )
                }
            }
        }
    )
}