package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic

sealed class ClassicDrawerUiState {

    /**
     * When there is no Draw running
     */
    object NotStarted: ClassicDrawerUiState()

    /**
     * When is loading data to set up the Ui State
     */
    object Loading: ClassicDrawerUiState()

    /**
     * When there's an error while loading data
     */
    data class Error(
        val errorMessage: Int
    ) : ClassicDrawerUiState()

    /**
     * When data was successfully loaded
     */
    data class Success(
        val drawId: Long,
        val isFinished: Boolean = false,
        val numbers: List<Int>,
        val drawnNumbers: List<Int>,
        val availableNumbers: List<Int>
    ) : ClassicDrawerUiState()
}