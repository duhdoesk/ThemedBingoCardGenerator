package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.screens.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state.CardSize

@Composable
fun ThemedCardSizeSwitcher(
    modifier: Modifier = Modifier,
    cardSize: CardSize = CardSize.LARGE,
    borderWidth: Dp = 1.dp,
    parentShape: Shape = CircleShape,
    toggleShape: Shape = CircleShape,
    animationDuration: Int = 300,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall,
    onClick: () -> Unit
) {

    BoxWithConstraints(
        modifier = modifier
            .clip(shape = parentShape)
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {

        val offset by animateDpAsState(
            targetValue = if (cardSize == CardSize.LARGE) (maxWidth / 2) else 0.dp,
            animationSpec = tween(
                durationMillis = animationDuration,
                easing = Ease
            ),
            label = "Offset"
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight()
                .offset(x = offset)
                .padding(all = 2.dp)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colorScheme.secondary)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = borderWidth,
                        color = MaterialTheme.colorScheme.secondary
                    ),
                    shape = parentShape
                )
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {

                val color by animateColorAsState(
                    targetValue = if (cardSize == CardSize.LARGE) MaterialTheme.colorScheme.onSecondaryContainer
                    else MaterialTheme.colorScheme.onSecondary,
                    animationSpec = tween(animationDuration),
                    label = "Color"
                )

                Text(
                    text = stringResource(CardSize.MEDIUM.stringResource),
                    style = textStyle,
                    color = color
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {

                val color by animateColorAsState(
                    targetValue = if (cardSize != CardSize.LARGE) MaterialTheme.colorScheme.onSecondaryContainer
                    else MaterialTheme.colorScheme.onSecondary,
                    animationSpec = tween(animationDuration),
                    label = "Color"
                )

                Text(
                    text = stringResource(CardSize.LARGE.stringResource),
                    style = textStyle,
                    color = color
                )
            }
        }
    }
}