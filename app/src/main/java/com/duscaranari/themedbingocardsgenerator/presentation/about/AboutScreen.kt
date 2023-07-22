package com.duscaranari.themedbingocardsgenerator.presentation.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.util.WindowInfo
import com.duscaranari.themedbingocardsgenerator.util.rememberWindowInfo

data class Link(
    val label: String,
    val uri: String,
    val icon: Int
)

@Composable
fun AboutScreen() {

    val windowInfo = rememberWindowInfo()

    when (windowInfo.screenWidthInfo) {
        is WindowInfo.WindowType.Compact -> CompactAboutScreen()
        else -> MediumAboutScreen()
    }
}

@Composable
fun CompactAboutScreen() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Logo()

        Description(
            Modifier.padding(24.dp))

        Spacer(
            Modifier.height(40.dp))

        SocialMedia(
            Modifier.padding(top = 8.dp))
    }
}

@Composable
fun MediumAboutScreen() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxSize()
    ) {

        Logo(
            Modifier.weight(1f))

        Description(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp))

        SocialMedia(
            Modifier.weight(1f))
    }
}


// COMMON COMPOSABLE FUNCTIONS

@Composable
fun Logo(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.about_screen_logo),
            contentDescription = stringResource(id = R.string.logo),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            text = stringResource(id = R.string.mobile_apps_development),
            style = MaterialTheme.typography.titleMedium,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun Description(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {

        Text(
            text = stringResource(id = R.string.about_desc),
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun SocialMedia(
    modifier: Modifier = Modifier,
    links: List<Link> = getLinks(),
    uriHandler: UriHandler = getUriHandler()
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
    ) {

        Text(
            text = stringResource(id = R.string.find_me),
            style = MaterialTheme.typography.labelLarge,
        )

        for (item in links) {

            Card(
                elevation = CardDefaults.cardElevation(4.dp),
                modifier = Modifier.clickable { uriHandler.openUri(item.uri) }
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .width(200.dp)
                        .padding(start = 4.dp)
                ) {

                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(32.dp)
                            .padding(4.dp)
                    )

                    Text(
                        text = item.label,
                        Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right_24),
                        contentDescription = "Arrow Right"
                    )
                }
            }
        }
    }
}


// UTILS

fun getLinks(): List<Link> {

    return listOf(
        Link(
            label = "LinkedIn",
            uri = "https://www.linkedin.com/in/edusc/",
            icon = R.drawable.icon_linkedin
        ),

        Link(
            label = "Instagram",
            uri = "https://www.instagram.com/duscaranari/",
            icon = R.drawable.icon_instagram
        )
    )
}

@Composable
fun getUriHandler(): UriHandler {
    return LocalUriHandler.current
}