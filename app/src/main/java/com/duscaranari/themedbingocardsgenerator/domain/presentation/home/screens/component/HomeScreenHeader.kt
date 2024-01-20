package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun HomeScreenHeader() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.hot_water_logo),
            contentDescription = "Hot Water Logo",
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {

            Text(
                text = stringResource(id = R.string.hot_water_software),
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = stringResource(id = R.string.mobile_apps_development),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )
        }
    }
}