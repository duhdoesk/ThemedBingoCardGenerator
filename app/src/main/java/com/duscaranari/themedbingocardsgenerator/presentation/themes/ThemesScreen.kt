package com.duscaranari.themedbingocardsgenerator.presentation.themes

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo
import com.duscaranari.themedbingocardsgenerator.util.showInterstitialAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

@Composable
fun ThemesScreen(
    navController: NavHostController,
    themesViewModel: ThemesViewModel = hiltViewModel()
) {

    when (val state = themesViewModel.themesState.collectAsState().value) {

        is ThemesState.Loading ->
            LoadingScreen()

        is ThemesState.Ready -> {

            val windowInfo = rememberWindowInfo()

            when (windowInfo.screenWidthInfo) {

                is WindowInfo.WindowType.Compact -> CompactThemesScreen(
                    navController = navController,
                    state = state,
                    context = LocalContext.current
                )

                else -> MediumThemesScreen(
                    navController = navController,
                    state = state,
                    context = LocalContext.current
                )
            }
        }
    }
}

@Composable
fun CompactThemesScreen(
    navController: NavHostController,
    state: ThemesState.Ready,
    context: Context
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TopLabel()
        Spacer(modifier = Modifier.height(8.dp))
        ThemesLazyVerticalGrid(
            themes = state.themesList,
            columns = 2,
            modifier = Modifier.fillMaxSize(),
            onClick = { theme ->
                showInterstitialAd(context)
                navController.navigate("${AppScreens.Card.name}/${theme.themeId}")
            })
    }
}

@Composable
fun MediumThemesScreen(navController: NavHostController, state: ThemesState.Ready, context: Context) {

    Column(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {

        Row {
            TopLabel()
        }

        Spacer(Modifier.height(8.dp))

        ThemesLazyVerticalGrid(
            themes = state.themesList,
            columns = 5,
            modifier = Modifier.fillMaxSize(),
            onClick = { theme ->
                showInterstitialAd(context)
                navController.navigate("${AppScreens.Card.name}/${theme.themeId}")
            })
    }
}

@Composable
fun TopLabel(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {

        Text(
            text = stringResource(id = R.string.available_themes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ThemesLazyVerticalGrid(
    themes: List<Theme>,
    columns: Int,
    modifier: Modifier = Modifier,
    onClick: (theme: Theme) -> Unit
) {

    Column(modifier = modifier) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            for (theme in themes) {
                item {
                    ThemesCard(
                        theme = theme,
                        onClick = { onClick(theme) }
                    )
                }
            }
        }
    }
}

@Composable
fun ThemesCard(
    theme: Theme,
    onClick: (theme: Theme) -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(theme) }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(theme.themePicture)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = "Theme Picture",
                placeholder = painterResource(id = R.drawable.compact_screen_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.1f)
            )

            Row(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                Text(
                    text = theme.themeName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
            }
        }
    }
}