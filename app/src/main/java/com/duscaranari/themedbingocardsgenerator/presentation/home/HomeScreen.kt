package com.duscaranari.themedbingocardsgenerator.presentation.home

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun HomeScreen(navController: NavHostController) {
    val windowInfo = rememberWindowInfo()
    when (windowInfo.screenWidthInfo) {
        is WindowInfo.WindowType.Compact -> CompactHomeScreen(navController)
        else -> MediumHomeScreen(navController)
    }
}

@Composable
fun CompactHomeScreen(navController: NavHostController) {
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
                .fillMaxSize()
                .padding(16.dp)
        ) {

            HeaderLabels()
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                HomeCardsRow(
                    navController = navController, modifier = Modifier
                        .weight(1f)
                        .padding(28.dp)
                )
            }
        }
    }
}

@Composable
fun MediumHomeScreen(navController: NavHostController) {
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
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            HeaderLabels()

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {

                HomeCardsRow(
                    navController = navController,
                    modifier = Modifier
                        .weight(1f)
                        .padding(28.dp)
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
    navController: NavHostController
) {
    HomeCard(
        label = stringResource(id = R.string.about_us),
        icon = R.drawable.baseline_groups_24,
        onClick = { navController.navigate(AppScreens.About.name) },
        modifier = modifier
    )

    HomeCard(
        label = stringResource(id = R.string.play),
        icon = R.drawable.baseline_emoji_nature_24,
        onClick = { navController.navigate(AppScreens.Themes.name) },
        modifier = modifier
    )
}