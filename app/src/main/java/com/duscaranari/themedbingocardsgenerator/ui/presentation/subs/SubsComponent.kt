package com.duscaranari.themedbingocardsgenerator.ui.presentation.subs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.android.billingclient.api.ProductDetails
import com.duscaranari.themedbingocardsgenerator.R

const val MONTHLY = "monthly-drawer-access"
const val TRIMESTER = "trimestral-drawer-access"
const val YEARLY = "annual-drawer-access"

@Composable
fun SubsPlanCardsColumn(
    onClick: (planId: String) -> Unit,
    offerDetails: List<ProductDetails.SubscriptionOfferDetails>?
) {

    val modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 16.dp)

    LazyColumn {

        item {
            SubsCard(
                plan = stringResource(id = R.string.monthly_plan),
                description = stringResource(id = R.string.monthly_plan_desc),
                onClick = { onClick(MONTHLY) },
                offerDetails = offerDetails?.find {
                    it.basePlanId == MONTHLY
                },
                modifier = modifier
            )
        }

        item {
            SubsCard(
                plan = stringResource(id = R.string.trimester_plan),
                description = stringResource(id = R.string.trimester_plan_desc),
                onClick = { onClick(TRIMESTER) },
                modifier = modifier,
                offerDetails = offerDetails?.find {
                    it.basePlanId == TRIMESTER
                })
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
                onClick = { onClick(YEARLY) },
                modifier = modifier,
                offerDetails = offerDetails?.find {
                    it.basePlanId == YEARLY
                }
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
    modifier: Modifier,
    offerDetails: ProductDetails.SubscriptionOfferDetails?
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
            .clickable { onClick() }
    ) {

        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 4.dp, bottom = 16.dp)
        ) {

            Row {

                Text(
                    text = plan,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )

                offerDetails?.let {
                    Text(
                        text = offerDetails.pricingPhases.pricingPhaseList.first().formattedPrice,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Row {
                Text(
                    text = description
                )
            }
        }
    }
}