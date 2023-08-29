package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

/**
 * Interface to handle Drawer Screen Ui State
 */
sealed class DrawerUiState {

    /**
     * When is loading data to set up the Ui State
     */
    object Loading : DrawerUiState()

    /**
     * When there's an error while loading data
     */
    data class Error(
        val errorMessage: Int
    ) : DrawerUiState()

    /**
     * When data was successfully loaded
     */
    data class Success(
        val drawId: Long,
        val isFinished: Boolean = false,
        val theme: Theme?,
        val themeCharacters: List<Character>?,
        val drawnCharacters: List<Character>?,
        val availableCharacters: List<Character>?
    ) : DrawerUiState()
}