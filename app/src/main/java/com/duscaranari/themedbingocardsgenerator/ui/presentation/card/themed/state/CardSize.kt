package com.duscaranari.themedbingocardsgenerator.ui.presentation.card.themed.state

import com.duscaranari.themedbingocardsgenerator.R

enum class CardSize(
    val characterAmount: Int,
    val stringResource: Int
) {
    MEDIUM(
        characterAmount = 6,
        stringResource = R.string.medium_card
    ),

    LARGE(
        characterAmount = 9,
        stringResource = R.string.large_card
    );

    fun next(): CardSize {
        val values = entries.toTypedArray()
        val nextOrdinal = (ordinal + 1) % values.size
        return values[nextOrdinal]
    }
}