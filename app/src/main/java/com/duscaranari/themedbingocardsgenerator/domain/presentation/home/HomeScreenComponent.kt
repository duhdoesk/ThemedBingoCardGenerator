package com.duscaranari.themedbingocardsgenerator.domain.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens

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
fun ThemedLabels() {
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
fun ClassicLabels() {
    Text(
        text = stringResource(id = R.string.classic_bingo),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ThemedBingoButtons(
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
                if (subscribed) {
                    onNavigate(AppScreens.Drawer.name)
                } else {
                    onNavigate(AppScreens.Subs.name)
                }
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
    }
}

@Composable
fun ClassicBingoButtons(
    modifier: Modifier = Modifier,
    onNavigate: (route: String) -> Unit,
) {

    val buttonModifier = Modifier
        .widthIn(min = 160.dp, max = 240.dp)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = { onNavigate(AppScreens.ClassicCard.name) },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        ) {
            Text(
                text = stringResource(id = AppScreens.ClassicCard.stringResource),
                color = MaterialTheme.colorScheme.onTertiary
            )
        }

        Button(
            onClick = { onNavigate(AppScreens.ClassicDrawer.name) },
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            )
        ) {
            Text(
                text = stringResource(id = AppScreens.ClassicDrawer.stringResource),
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
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