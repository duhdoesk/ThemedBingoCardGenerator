package com.duscaranari.themedbingocardsgenerator.domain.presentation.card.classic.screens.component

fun getRandomCard(): List<Int> {

    val numbers= mutableListOf<Int>()
    numbers.addAll(0, (61..75).toList().shuffled().subList(0, 5))
    numbers.addAll(0, (46..60).toList().shuffled().subList(0, 5))
    numbers.addAll(0, (31..45).toList().shuffled().subList(0, 4))
    numbers.addAll(0, (16..30).toList().shuffled().subList(0, 5))
    numbers.addAll(0, (1..15).toList().shuffled().subList(0, 5))

    return numbers
}