package com.duscaranari.themedbingocardsgenerator.presentation.card

import androidx.lifecycle.ViewModel
import com.duscaranari.themedbingocardsgenerator.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val characterRepository: CharacterRepository
) : ViewModel() {
}