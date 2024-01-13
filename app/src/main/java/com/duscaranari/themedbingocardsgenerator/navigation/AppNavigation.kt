package com.duscaranari.themedbingocardsgenerator.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.domain.presentation.about.AboutScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.ClassicCardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.card.themed.CardScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.characters.CharacterScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.ClassicDrawerScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.themed.DrawerScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.HomeScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.BingoType
import com.duscaranari.themedbingocardsgenerator.domain.presentation.subs.SubsScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.ThemesScreen
import com.duscaranari.themedbingocardsgenerator.util.AdmobBanner
import com.duscaranari.themedbingocardsgenerator.util.billing.BillingHelper
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    billingHelper: BillingHelper,
    subscribed: Boolean,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?,
    onBingoTypeChange: (bingoType: BingoType) -> Unit
) {

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = AppScreens.entries.find {
        it.name == backStackEntry?.destination?.route?.substringBefore("/")
    } ?: AppScreens.Home

    Scaffold(
        topBar = {
            ThemedBingoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        },
        bottomBar = {
            if (!subscribed && rememberDeviceOrientation() == DeviceOrientation.Portrait) {
                BottomBar()
            }
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
                    subscribed,
                    onBingoTypeChange = { onBingoTypeChange(it) }
                )
            }

            composable(AppScreens.Themes.name) {
                ThemesScreen(navController, subscribed)
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
                    offerDetails,
                    navController
                )
            }

            composable(AppScreens.ClassicDrawer.name) {
                ClassicDrawerScreen()
            }

            composable(AppScreens.ClassicCard.name) {
                ClassicCardScreen()
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

@Composable
fun BottomBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp, max = 80.dp)
    ) {
        AdmobBanner()
    }
}