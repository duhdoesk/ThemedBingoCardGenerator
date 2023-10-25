package com.duscaranari.themedbingocardsgenerator.domain.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.AdmobBanner
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun HomeScreen(
    navController: NavHostController,
    subscribed: Boolean
) {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed
            )

        else ->
            LandscapeHomeScreen(
                onNavigate = { navController.navigate(it) },
                subscribed
            )
    }
}

@Composable
fun PortraitHomeScreen(onNavigate: (route: String) -> Unit, subscribed: Boolean) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            HeaderLabels()

            Image(
                painter = painterResource(id = R.drawable.smiling_squirrel),
                contentDescription = "Smiling Squirrel",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(132.dp)
                    .padding(vertical = 16.dp)
            )

            HomeButtons(
                onNavigate = { onNavigate(it) },
                subscribed = subscribed,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        if (!subscribed) {
            SubscriptionButton(onNavigate = { onNavigate(it) })
        }
    }
}

@Composable
fun LandscapeHomeScreen(onNavigate: (route: String) -> Unit, subscribed: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LogoPicture(
            maxWidth = 0.4f,
            maxHeight = 1f
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            HeaderLabels()

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                HomeButtons(
                    modifier = Modifier.fillMaxWidth(),
                    onNavigate = { onNavigate(it) },
                    subscribed = subscribed
                )
            }

            if (!subscribed) {
                SubscriptionButton(onNavigate = { onNavigate(it) })
            }
        }
    }
}


// COMMON COMPOSABLE FUNCTIONS

@Composable
fun LogoPicture(
    maxWidth: Float,
    maxHeight: Float
) {
    Image(
        painter = painterResource(id = R.drawable.compact_screen_logo),
        contentDescription = stringResource(id = R.string.logo),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(maxWidth)
            .fillMaxHeight(maxHeight)
            .shadow(16.dp)
    )
}

@Composable
fun HeaderLabels() {
    Text(
        text = stringResource(id = R.string.app_name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
    )

    Text(
        text = stringResource(id = R.string.built_by),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun HomeButtons(
    modifier: Modifier = Modifier,
    onNavigate: (route: String) -> Unit,
    subscribed: Boolean
) {

    val buttonModifier = Modifier
        .widthIn(min = 160.dp, max = 240.dp)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = { onNavigate("${AppScreens.Themes.name}/${AppScreens.Card.name}") },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = stringResource(id = AppScreens.Card.stringResource))
        }

        Button(
            onClick = {
                if (subscribed) { onNavigate(AppScreens.Drawer.name) }
                else { onNavigate(AppScreens.Subs.name) }
            },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            if (!subscribed) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Lock",
                    modifier = Modifier.padding(end = 6.dp)
                )
            }

            Text(text = stringResource(id = AppScreens.Drawer.stringResource))
        }

        TextButton(
            onClick = { onNavigate(AppScreens.About.name) },
            modifier = buttonModifier
        ) {
            Text(text = stringResource(id = AppScreens.About.stringResource))
        }
    }
}

@Composable
fun SubscriptionButton(onNavigate: (route: String) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(
            onClick = { onNavigate(AppScreens.Subs.name) },
            modifier = Modifier
                .widthIn(min = 160.dp, max = 240.dp)
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Lock",
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = stringResource(id = R.string.drawer_unlock),
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}


// PREVIEWS

@PortraitPreviews
@Composable
fun ScreenPreview() {
    PortraitHomeScreen(onNavigate = { }, subscribed = true)
}