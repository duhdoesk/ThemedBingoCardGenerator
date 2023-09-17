package com.duscaranari.themedbingocardsgenerator.presentation.subs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun SubsPlanCardsColumn(
    onClick: (planId: String) -> Unit
) {

    val cardModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 16.dp)

    LazyColumn {

        item {
            SubsCard(
                plan = stringResource(id = R.string.monthly_plan),
                description = stringResource(id = R.string.monthly_plan_desc),
                onClick = { onClick("monthly-drawer-access") },
                cardModifier = cardModifier
            )
        }

        item {
            SubsCard(
                plan = stringResource(id = R.string.trimester_plan),
                description = stringResource(id = R.string.trimester_plan_desc),
                onClick = { onClick("trimestral-drawer-access") },
                cardModifier = cardModifier
            )
        }

        item {
            Text(
                text = stringResource(id = R.string.best_option),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        }

        item {
            SubsCard(
                plan = stringResource(id = R.string.yearly_plan),
                description = stringResource(id = R.string.yearly_plan_desc),
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = { onClick("annual-drawer-access") },
                cardModifier = cardModifier
            )
        }
    }
}

@Composable
private fun SubsCard(
    plan: String,
    description: String,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit,
    cardModifier: Modifier
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = cardModifier
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