package com.duscaranari.themedbingocardsgenerator.presentation.subs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.R

@Composable
fun SubsPlanCards(
    onClick: (planId: String) -> Unit
) {

    Column {

        SubsCard(
            plan = stringResource(id = R.string.monthly_plan),
            description = stringResource(id = R.string.monthly_plan_desc),
            onClick = { onClick("monthly-drawer-access") }
        )

        SubsCard(
            plan = stringResource(id = R.string.trimester_plan),
            description = stringResource(id = R.string.trimester_plan_desc),
            onClick = { onClick("trimestral-drawer-access") }
        )

        Text(
            text = stringResource(id = R.string.best_option),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        SubsCard(
            plan = stringResource(id = R.string.yearly_plan),
            description = stringResource(id = R.string.yearly_plan_desc),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            onClick = { onClick("annual-drawer-access") }
        )
    }
}

@Composable
private fun SubsCard(
    plan: String,
    description: String,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 4.dp, bottom = 16.dp)
        ) {
            Text(
                text = plan,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = description
            )
        }
    }
}