package com.duscaranari.themedbingocardsgenerator.presentation.home

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd

@Composable
fun HomeScreen(navController: NavHostController) {

    showInterstitialAd(LocalContext.current)

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait ->
            PortraitHomeScreen(onNavigate = { navController.navigate(it) })

        else ->
            LandscapeHomeScreen(onNavigate = { navController.navigate(it) })
    }
}

@Composable
fun PortraitHomeScreen(onNavigate: (route: String) -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        LogoPicture(
            maxWidth = 1f,
            maxHeight = 0.3f
        )

        Column(
            verticalArrangement = Arrangement.Top,
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
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LandscapeHomeScreen(onNavigate: (route: String) -> Unit) {
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

                HomeCardsRow(
                    onNavigate = { onNavigate(it) },
                    modifier = Modifier
                        .widthIn(min = 280.dp, max = 360.dp)
                        .padding(horizontal = 28.dp)
                )
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
fun HomeCard(
    label: String,
    icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = modifier.clickable { onClick() }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .aspectRatio(1.1f)
                    .padding(8.dp)
            )

            Row(
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            ) {

                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@Composable
fun HomeCardsRow(
    modifier: Modifier = Modifier,
    onNavigate: (route: String) -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {

        HomeCard(
            label = stringResource(id = R.string.about_us),
            icon = R.drawable.baseline_groups_24,
            onClick = { onNavigate(AppScreens.About.name) },
            modifier = Modifier.weight(1f)
        )

        HomeCard(
            label = stringResource(id = R.string.play),
            icon = R.drawable.baseline_emoji_nature_24,
            onClick = { onNavigate(AppScreens.Themes.name) },
            modifier = Modifier.weight(1f)
        )
    }

}

@Composable
fun HomeButtons(
    modifier: Modifier = Modifier,
    onNavigate: (route: String) -> Unit
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
            Text(text = AppScreens.Card.name)
        }

        Button(
            onClick = { onNavigate("${AppScreens.Themes.name}/${AppScreens.Drawer.name}") },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(text = AppScreens.Themes.name)
        }

        TextButton(
            onClick = { onNavigate(AppScreens.About.name) },
            modifier = buttonModifier
        ) {
            Text(text = AppScreens.About.name)
        }
    }
}


// PREVIEWS

@PortraitPreviews
@Composable
fun ScreenPreview() {
    PortraitHomeScreen(onNavigate = { })
}