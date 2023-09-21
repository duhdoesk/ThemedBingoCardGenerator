package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.presentation.component.ErrorScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.ThemesScreen
import com.duscaranari.themedbingocardsgenerator.presentation.component.getRawListOfCharacters
import com.duscaranari.themedbingocardsgenerator.presentation.component.getRawTheme
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
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
                    onStartNewDraw = { drawerViewModel.stateNotStarted() },
                    state = state
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(stringResource(id = R.string.finish_draw_dialog_title)) },
            text = { Text(stringResource(id = R.string.finish_draw_dialog_body)) },
            confirmButton = {
                TextButton(onClick = {
                    drawerViewModel.finishDraw()
                    showDialog = false
                }) {
                    Text(stringResource(id = R.string.confirm).uppercase())
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(id = R.string.cancel).uppercase())
                }
            },
        )
    }
}


/**
 * PORTRAIT FUNCTION
 */

@Composable
fun PortraitDrawerScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    val character = state.drawnCharacters.lastOrNull()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .sizeIn(
                    maxWidth = 600.dp,
                    maxHeight = 1000.dp
                )
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp)
            ) {

                Spacer(Modifier.weight(1f))

                DrawerThemeText(
                    text = state.theme?.themeName.orEmpty(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(16.dp))

                DrawerCounterText(
                    text = "${state.drawnCharacters.size} / ${state.themeCharacters.size}",
                    modifier = Modifier.fillMaxWidth()
                )

                character?.let { DrawerCharacterImageAndName(character = it) }

                Spacer(Modifier.height(32.dp))

                DrawerButtons(
                    isFinished = state.isFinished,
                    onDrawNextCharacter = onDrawNextCharacter,
                    onFinishDraw = onFinishDraw,
                    onStartNewDraw = onStartNewDraw
                )

                Spacer(Modifier.height(16.dp))

                DrawerDrawnText(Modifier.fillMaxWidth())

                DrawerLazyGrid(
                    characters = state.drawnCharacters.reversed(),
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }
        }
    }
}


/**
 * LANDSCAPE FUNCTION
 */

@Composable
fun LandscapeDrawerScreen(
    onNavigate: () -> Unit,
    onDrawNextCharacter: () -> Unit,
    onFinishDraw: () -> Unit,
    onStartNewDraw: () -> Unit,
    state: DrawerUiState.Success
) {

    val character = state.drawnCharacters.lastOrNull()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .sizeIn(
                    maxWidth = 1000.dp,
                    maxHeight = 600.dp
                )
                .padding(4.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {

                DrawerDrawnText(Modifier.fillMaxWidth())

                DrawerLazyGrid(
                    characters = state.drawnCharacters.reversed(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .heightIn(max = 500.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f)
            ) {

                DrawerCounterText(
                    text = "${state.drawnCharacters.size} / ${state.themeCharacters.size}",
                    modifier = Modifier.fillMaxWidth()
                )

                character?.let { DrawerCharacterImageAndName(character = it) }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .heightIn(max = 500.dp)
                    .weight(1.5f)
            ) {

                DrawerThemeText(
                    text = state.theme?.themeName.orEmpty(),
                    modifier = Modifier.fillMaxWidth(0.6f)
                )

                Spacer(Modifier.weight(1f))

                DrawerButtons(
                    isFinished = state.isFinished,
                    onDrawNextCharacter = onDrawNextCharacter,
                    onFinishDraw = onFinishDraw,
                    onStartNewDraw = onStartNewDraw
                )

                Spacer(Modifier.weight(1f))
            }
        }
    }
}


/**
 * PREVIEWS
 */

/**
@PortraitPreviews
@Composable
fun PortraitPreview() {
PortraitDrawerScreen(
onNavigate = { },
onDrawNextCharacter = { },
onFinishDraw = { },
onStartNewDraw = { },
state = DrawerUiState.Success(
drawId = 1,
isFinished = true,
theme = getRawTheme(),
themeCharacters = getRawListOfCharacters(),
availableCharacters = getRawListOfCharacters().subList(0, 8),
drawnCharacters = getRawListOfCharacters().subList(0, 8)
)
)
}
 */

@LandscapePreviews
@Composable
fun LandscapePreviews() {
    LandscapeDrawerScreen(
        onNavigate = { },
        onDrawNextCharacter = { },
        onFinishDraw = { },
        onStartNewDraw = { },
        state = DrawerUiState.Success(
            drawId = 1,
            isFinished = true,
            theme = getRawTheme(),
            themeCharacters = getRawListOfCharacters(),
            availableCharacters = getRawListOfCharacters().subList(0, 8),
            drawnCharacters = getRawListOfCharacters().subList(0, 8)
        )
    )
}