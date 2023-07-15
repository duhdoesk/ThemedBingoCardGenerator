package com.duscaranari.themedbingocardsgenerator.presentation.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontStyle
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
        else -> ExpandedHomeScreen(navController)
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

        Image(
            painter = painterResource(id = R.drawable.compact_screen_logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .shadow(16.dp)
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Themed Bingo Cards Generator",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = "Built by ES apps",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                HomeCard(
                    label = "About Us",
                    icon = R.drawable.baseline_groups_24,
                    onClick = { navController.navigate(AppScreens.About.name) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(28.dp)
                )

                HomeCard(
                    label = "Play",
                    icon = R.drawable.baseline_emoji_nature_24,
                    onClick = { navController.navigate(AppScreens.Themes.name) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(28.dp),

                    )
            }
        }
    }
}

@Composable
fun ExpandedHomeScreen(navController: NavHostController) {
    Text(text = "Expanded")
}

@Composable
@Preview(showSystemUi = true)
fun CompactPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Image(
            painter = painterResource(id = R.drawable.compact_screen_logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .shadow(16.dp)
        )

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Text(
                text = "Themed Bingo Cards Generator",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = "Built by ES apps",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {

                HomeCard(
                    label = "About Us",
                    icon = R.drawable.baseline_groups_24,
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(28.dp)
                )

                HomeCard(
                    label = "Play",
                    icon = R.drawable.baseline_emoji_nature_24,
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(28.dp),

                )
            }
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
fun ExpandedPreview() {

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