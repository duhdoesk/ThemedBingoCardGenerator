package com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.classic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.domain.model.ClassicDraw
import com.duscaranari.themedbingocardsgenerator.domain.presentation.drawer.themed.DrawerUiState
import com.duscaranari.themedbingocardsgenerator.domain.repository.ClassicDrawRepository
import com.duscaranari.themedbingocardsgenerator.util.funLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassicDrawerViewModel @Inject constructor(
    private val classicDrawRepository: ClassicDrawRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ClassicDrawerUiState>(ClassicDrawerUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        funLogger("init")
        checkSavedState()
    }


    /**
     * UI Logic
     */

    private fun checkSavedState() {
        funLogger("DrawerViewModel checkSavedState")
        viewModelScope.launch(Dispatchers.IO) {

            when (val lastDraw = getLastDraw()) {
                null -> startNewDraw(75)
                else -> refreshDrawState(lastDraw.drawId)
            }
        }
    }

    private suspend fun refreshDrawState(drawId: Long) {
        funLogger("refreshDrawState")

        val draw = getDrawById(drawId)!!

        val numbers = (1..draw.numbers).toList()

        val drawnNumbers =
            if (draw.drawnNumbers == "") emptyList()
            else draw.drawnNumbers.split(",").filterNot { it == "" }.map { it.toInt() }

        val availableNumbers = numbers.filterNot { it in drawnNumbers }

        when (val state = _uiState.value) {
            is ClassicDrawerUiState.Success -> {
                if (!state.isFinished && drawnNumbers.size == numbers.size) {
                    finishDraw()
                }
            }

            else -> {}
        }

        _uiState.value = ClassicDrawerUiState.Success(
            drawId = draw.drawId,
            isFinished = draw.isCompleted,
            numbers = numbers,
            drawnNumbers = drawnNumbers,
            availableNumbers = availableNumbers
        )
    }


    /**
     * Business Logic
     */

    fun drawNextNumber() {
        funLogger("drawNextNumber")

        viewModelScope.launch(Dispatchers.IO) {
            when (val state = uiState.value) {
                is ClassicDrawerUiState.Success -> {
                    val nextNumber = state.availableNumbers.shuffled().first()

                    setDrawnNumbers(
                        drawId = state.drawId,
                        drawnNumbers = "${getDrawnNumbers(state.drawId)}$nextNumber,"
                    )

                    refreshDrawState(state.drawId)
                }

                else -> return@launch
            }
        }
    }

    fun finishDraw() {
        funLogger("finishDraw")

        viewModelScope.launch(Dispatchers.IO) {
            when (val state = uiState.value) {
                is ClassicDrawerUiState.Success -> {
                    state.drawId.let {
                        finishDraw(it)
                        refreshDrawState(it)
                    }
                }

                else -> return@launch
            }
        }
    }

    fun startNewDraw(numbers: Int) {
        funLogger("startNewDraw")

        viewModelScope.launch(Dispatchers.IO) {
            val drawId = createNewDraw(
                ClassicDraw(
                    numbers = numbers,
                    drawnNumbers = "",
                    isCompleted = false
                )
            )

            refreshDrawState(drawId)
        }
    }

    fun getStringOfDrawnNumbers(): String {
        var drawnString = "*CONFERÊNCIA*\n"
        var count = 1

        when (val state = uiState.value) {
            is ClassicDrawerUiState.Success -> {
                for (number in state.drawnNumbers) {
                    drawnString = drawnString + "\n" +
                            "*$count* - Pedra $number"

                    count += 1
                }
            }

            else -> {}
        }

        return drawnString
    }


    /**
     * Repository Functions
     */

    private suspend fun getDrawById(drawId: Long): ClassicDraw? {
        return classicDrawRepository.getDrawById(drawId)
    }

    private suspend fun getLastDraw(): ClassicDraw? {
        return classicDrawRepository.getLastDraw()
    }

    private suspend fun finishDraw(drawId: Long) {
        classicDrawRepository.finishDraw(drawId)
    }

    private suspend fun createNewDraw(draw: ClassicDraw): Long {
        return classicDrawRepository.createNewDraw(draw)
    }

    private suspend fun getDrawnNumbers(drawId: Long): String {
        return classicDrawRepository.getDrawnNumbers(drawId)
    }

    private suspend fun setDrawnNumbers(drawId: Long, drawnNumbers: String) {
        classicDrawRepository.setDrawnNumbers(drawId, drawnNumbers)
    }
}