package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic.screens.component

import android.graphics.drawable.shapes.Shape
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duscaranari.themedbingocardsgenerator.ui.theme.SphereBrown

@Composable
fun DrawnSpheresLazyGrid(
    modifier: Modifier = Modifier,
    drawnNumbers: List<Int>,
    minSize: Dp,
    itemsSpacing: Dp
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .border(
                width = 2.dp,
                color = SphereBrown,
                shape = RoundedCornerShape(12.dp))
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = minSize),
            verticalArrangement = Arrangement.spacedBy(itemsSpacing),
            horizontalArrangement = Arrangement.spacedBy(itemsSpacing),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier
        ) {
            for (number in drawnNumbers) {
                item {
                    BingoSphere(
                        sphereSize = 48.dp,
                        canvasRadius = 43.2f,
                        canvasStroke = 4.8f,
                        fontSize = 19.sp,
                        number = number.toString()
                    )
                }
            }
        }
    }

}