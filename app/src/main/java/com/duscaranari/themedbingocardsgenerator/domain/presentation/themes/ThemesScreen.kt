package com.duscaranari.themedbingocardsgenerator.domain.presentation.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.LoadingScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.component.getRawListOfThemes
import com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.screens.LandscapeThemesScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.screens.NoDataThemesScreen
import com.duscaranari.themedbingocardsgenerator.domain.presentation.themes.screens.PortraitThemesScreen
import com.duscaranari.themedbingocardsgenerator.navigation.AppScreens
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.ui.theme.PortraitPreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

@Composable
fun ThemesScreen(
    navController: NavHostController,
    subscribed: Boolean,
    themesViewModel: ThemesViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    when (val state = themesViewModel.themesState.collectAsState().value) {

        is ThemesState.Loading ->
            LoadingScreen()

        is ThemesState.NoData -> {
            NoDataThemesScreen(
                onRefresh = { themesViewModel.checkData() }
            )
        }

        is ThemesState.Ready -> {

            when (rememberDeviceOrientation()) {
                is DeviceOrientation.Portrait ->
                    PortraitThemesScreen(
                        onNavigate = {
                            navController.navigate("${AppScreens.Card.name}/${it}")
                        },
                        themes = state.themesList,
                        subscribed = subscribed,
                        context = context
                    )
                else -> LandscapeThemesScreen(
                    onNavigate = {
                        navController.navigate("${AppScreens.Card.name}/${it}")
                    },
                    themes = state.themesList,
                    subscribed = subscribed,
                    context = context
                )
            }
        }
    }
}


/**
 * PREVIEWS
 */

@PortraitPreviews
@Composable
fun ThemesScreenPortraitPreview() {
    val context = LocalContext.current
    val themes = getRawListOfThemes()

    PortraitThemesScreen(onNavigate = { }, themes = themes, subscribed = true, context = context)
}

@LandscapePreviews
@Composable
fun ThemesScreenLandscapePreview() {
    val context = LocalContext.current
    val themes = getRawListOfThemes()

    LandscapeThemesScreen(onNavigate = { }, themes = themes, subscribed = true, context = context)
}