package com.duscaranari.themedbingocardsgenerator.ui.navigation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.ads.AdmobBanner
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo
import com.google.android.gms.ads.AdSize

@Composable
fun BottomBar() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp, max = 80.dp)
    ) {
        AdmobBanner(
            adSize =
            if (rememberDeviceOrientation() == DeviceOrientation.Portrait) {
                when (rememberWindowInfo().screenHeightInfo) {
                    is WindowInfo.WindowType.Expanded -> AdSize.LEADERBOARD
                    else -> AdSize.BANNER
                }
            }

            else {
                when (rememberWindowInfo().screenHeightInfo) {
                    is WindowInfo.WindowType.Compact -> AdSize.BANNER
                    else -> AdSize.LEADERBOARD
                }
            }
        )
    }
}