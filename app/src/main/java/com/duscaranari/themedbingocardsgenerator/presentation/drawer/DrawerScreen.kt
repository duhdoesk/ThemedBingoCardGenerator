package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.duscaranari.themedbingocardsgenerator.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.ThemesScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.getImageLoader
import com.duscaranari.themedbingocardsgenerator.presentation.component.getRawListOfCharacters
import com.duscaranari.themedbingocardsgenerator.presentation.component.getRawTheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.AdmobBanner
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

@Composable
fun DrawerScreen(
    navController: NavHostController,
    drawerViewModel: DrawerViewModel = hiltViewModel()
) {

    var showDialog by remember { mutableStateOf(false) }

    when (val state = drawerViewModel.uiState.collectAsState().value) {

        is DrawerUiState.Loading ->
            LoadingScreen()

        is DrawerUiState.Error ->
            ErrorScreen(
                errorMessage = state.errorMessage,
                onTryAgain = { drawerViewModel.checkSavedState() }
            )

        is DrawerUiState.NotStarted -> {
            ThemesScreen(themes = state.themes, onClick = { drawerViewModel.startNewDraw(it) })
        }

        is DrawerUiState.Success -> {
            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Portrait -> PortraitDrawerScreen(
                    onNavigate = { },
                    onDrawNextCharacter = { drawerViewModel.drawNextCharacter() },
                    onFinishDraw = { showDialog = true },
                    onStartNewDraw = { drawerViewModel.stateNotStarted() },
                    state = state
                )

                is DeviceOrientation.Landscape -> LandscapeDrawerScreen(
                    onNavigate = { },
                    onDrawNextCharacter = { drawerViewModel.drawNextCharacter() },
                    onFinishDraw = { showDialog = true },
                    state = state
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Are you sure you want to delete this?") },
            text = { Text("This action cannot be undone") },
            confirmButton = {
                TextButton(onClick = {
                    drawerViewModel.finishDraw()
                    showDialog = false
                }) {
                    Text("Delete it".uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel".uppercase())
                }
            },
        )
    }
}


/**
 * PORTRAIT FUNCTIONS
 */

@Composable
fun PortraitDrawerScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            PortraitCompactScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                onStartNewDraw = onStartNewDraw,
                state = state
            )

        else ->
            PortraitMediumScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )
    }
}

@Composable
fun PortraitCompactScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    val character = state.drawnCharacters.lastOrNull()

    Column(Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp)
        ) {

            Text(
                text = "${stringResource(id = R.string.selected_theme)}: ${state.theme?.themeName}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "${state.drawnCharacters.size} / ${state.themeCharacters.size}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth()
            )

            character?.let {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.characterPicture)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    placeholder = painterResource(id = R.drawable.compact_screen_logo),
                    contentDescription = "Character Picture",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(12.dp))
                        .sizeIn(
                            minWidth = 240.dp,
                            maxWidth = 320.dp,
                            minHeight = 240.dp,
                            maxHeight = 280.dp
                        ),
                    imageLoader = getImageLoader(LocalContext.current)
                )

                Text(
                    text = character.characterName,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(32.dp))

            DrawerButtons(
                isFinished = state.isFinished,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                onStartNewDraw = onStartNewDraw
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.drawn),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth()
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {

                for (c in state.drawnCharacters.reversed()) {

                    item {
                        Card {
                            Text(
                                text = c.characterName.uppercase(),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            AdmobBanner()
        }
    }
}

@Composable
fun PortraitMediumScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}


/**
 * LANDSCAPE FUNCTIONS
 */

@Composable
fun LandscapeDrawerScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    when (rememberWindowInfo().screenWidthInfo) {

        is WindowInfo.WindowType.Compact ->
            LandscapeCompactScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )

        else ->
            LandscapeMediumScreen(
                onNavigate = onNavigate,
                onDrawNextCharacter = onDrawNextCharacter,
                onFinishDraw = onFinishDraw,
                state = state
            )
    }
}

@Composable
fun LandscapeCompactScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}

@Composable
fun LandscapeMediumScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    state: DrawerUiState.Success
) {

}


/**
 * PREVIEWS
 */

@PortraitPreviews
@Composable
fun PortraitPreview() {
    PortraitCompactScreen(
        onNavigate = { },
        onDrawNextCharacter = { },
        onFinishDraw = { },
        onStartNewDraw = { },
        state = DrawerUiState.Success(
            drawId = 1,
            isFinished = false,
            theme = getRawTheme(),
            themeCharacters = getRawListOfCharacters(),
            availableCharacters = getRawListOfCharacters().subList(0, 3),
            drawnCharacters = getRawListOfCharacters().subList(3, 8)
        )
    )
}