package com.duscaranari.themedbingocardsgenerator.domain.presentation.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import com.duscaranari.themedbingocardsgenerator.ui.theme.LandscapePreviews
import com.duscaranari.themedbingocardsgenerator.util.DeviceOrientation
import com.duscaranari.themedbingocardsgenerator.util.rememberDeviceOrientation

data class Link(
    val label: String,
    val uri: String,
    val icon: Int
)

@Composable
fun AboutScreen() {

    when (rememberDeviceOrientation()) {
        is DeviceOrientation.Portrait -> PortraitAboutScreen()
        else -> LandscapeAboutScreen()
    }
}

@Composable
fun PortraitAboutScreen() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Logo()

        Description(
            Modifier.padding(24.dp)
        )

        Spacer(
            Modifier.height(40.dp)
        )

        SocialMedia(
            Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun LandscapeAboutScreen() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .heightIn(min = 360.dp, max = 480.dp)
                .widthIn(min = 480.dp, max = 600.dp)
                .padding(8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {

                Logo(
                    Modifier.weight(1f)
                )

                SocialMedia(
                    Modifier.weight(1f)
                )
            }

            Row {

                Description(
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
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
                        .padding(6.dp)
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


// PREVIEWS

@LandscapePreviews
@Composable
fun LandscapeAboutScreenPreview() {

    LandscapeAboutScreen()
}