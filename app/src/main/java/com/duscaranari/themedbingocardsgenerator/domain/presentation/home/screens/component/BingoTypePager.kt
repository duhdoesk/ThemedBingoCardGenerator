package com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.domain.presentation.home.screens.component.card.BingoTypeCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BingoTypePager(
    bingoTypes: List<BingoType>,
    onNavigate: (route: String) -> Unit,
    onBingoTypeChange: (bingoType: BingoType) -> Unit,
    modifier: Modifier = Modifier,
    isSubscribed: Boolean
) {

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { bingoTypes.size }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            onBingoTypeChange(bingoTypes[page])
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 12.dp,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
            modifier = modifier
        ) { page ->
            BingoTypeCard(
                bingoType = bingoTypes[page],
                onNavigate = { onNavigate(it) },
                isSubscribed = isSubscribed,
                cardModifier = Modifier
                    .fillMaxWidth(),
                pictureModifier = Modifier
                    .fillMaxWidth(0.4f)
                    .aspectRatio(1f),
                buttonModifier = Modifier
                    .width(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration)
                    MaterialTheme.colorScheme.primary else Color.LightGray

                val size = if (pagerState.currentPage == iteration)
                    10.dp else 8.dp

                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(size)
                )
            }
        }
    }
}

@Preview
@Composable
fun BingoTypePagerPreview() {
    BingoTypePager(
        bingoTypes = BingoType.getBingoTypes(),
        onBingoTypeChange = { },
        onNavigate = { },
        isSubscribed = false)
}