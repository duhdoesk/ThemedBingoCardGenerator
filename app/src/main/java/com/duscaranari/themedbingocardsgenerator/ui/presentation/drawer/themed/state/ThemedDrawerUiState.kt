package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed.state

import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme

/**
 * Interface to handle Drawer Screen Ui State
 */
sealed class ThemedDrawerUiState {

    /**
     * When there is no Draw running
     */
    data class NotStarted(
        val themes: List<BingoTheme>
    ) : ThemedDrawerUiState()

    /**
     * When is loading data to set up the Ui State
     */
    data object Loading : ThemedDrawerUiState()

    /**
     * When there's an error while loading data
     */
    data class Error(
        val errorMessage: Int
    ) : ThemedDrawerUiState()

    /**
     * When data was successfully loaded
     */
    data class Success(
        val drawId: Long,
        val isFinished: Boolean = false,
        val theme: BingoTheme,
        val drawnCharacters: List<BingoCharacter>,
        val availableCharacters: List<BingoCharacter>
    ) : ThemedDrawerUiState()
}