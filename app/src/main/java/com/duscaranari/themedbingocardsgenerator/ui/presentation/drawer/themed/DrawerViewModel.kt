package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.model.Draw
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.repository.DrawRepository
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllBingoThemesUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed.state.ThemedDrawerUiState
import com.duscaranari.themedbingocardsgenerator.util.funLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val drawRepository: DrawRepository,
    getAllBingoThemesUseCase: GetAllBingoThemesUseCase
) : ViewModel() {

    private val _currentDraw = MutableStateFlow<Draw?>(null)

    private val _themes = getAllBingoThemesUseCase.invoke()
    private val themes = _themes.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )

    val uiState = combine(
        _currentDraw,
        themes
    ) { draw, themes ->
        getState(draw, themes)
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ThemedDrawerUiState.Loading
        )

    private fun getState(draw: Draw?, themes: List<BingoTheme>): ThemedDrawerUiState {
        when (themes.isEmpty()) {
            true ->
                return ThemedDrawerUiState.Error(errorMessage = R.string.draw_error)

            false -> {
                when (draw) {
                    null ->
                        return ThemedDrawerUiState.NotStarted(themes)

                    else -> {
                        val theme = themes.first { it.id == draw.themeId }
                        val drawnCharacters = theme.characters.filter {
                            it.id in draw.drawnCharactersIdList.split(",")
                        }
                        val availableCharacters = theme.characters.filterNot {
                            it.id in draw.drawnCharactersIdList.split(",")
                        }

                        return ThemedDrawerUiState.Success(
                            drawId = draw.drawId,
                            theme = theme,
                            availableCharacters = availableCharacters,
                            drawnCharacters = drawnCharacters,
                            isFinished = draw.drawCompleted
                        )
                    }
                }
            }
        }
    }

    private val _jdsaklaf = MutableStateFlow<ThemedDrawerUiState>(ThemedDrawerUiState.Loading)
    val dhaugof = _jdsaklaf.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _currentDraw.update { getLastDraw() }
        }
    }

    /**
     * UI Logic
     */

    fun checkSavedState() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val lastDraw = getLastDraw()) {
                null -> _jdsaklaf.value =
                    ThemedDrawerUiState.NotStarted(themes = themes.value)

                else -> refreshDrawState(lastDraw.drawId)
            }
        }
    }

    private suspend fun refreshDrawState(drawId: Long) {
        funLogger("refreshDrawState")

        val draw = getDrawById(drawId)

        if (draw != null) {

            themes
            val theme = themes.value.first { it.id == draw.themeId }
            val themeCharacters = theme.characters

            val drawnCharactersIdList = draw.drawnCharactersIdList.split(",")
            val drawnCharactersList = mutableListOf<BingoCharacter>()

            for (id in drawnCharactersIdList) {
                themeCharacters.find { it.id == id }?.let {
                    drawnCharactersList.add(it)
                }
            }

            when (val state = _jdsaklaf.value) {
                is ThemedDrawerUiState.Success -> {
                    if (!state.isFinished &&
                        drawnCharactersList.size == themeCharacters.size &&
                        _jdsaklaf.value !is ThemedDrawerUiState.NotStarted
                    ) {
                        finishDraw()
                    }
                }

                else -> {}
            }


            _jdsaklaf.value = ThemedDrawerUiState.Success(
                drawId = draw.drawId,
                isFinished = draw.drawCompleted,
                theme = theme,
                drawnCharacters = drawnCharactersList,
                availableCharacters = themeCharacters.filterNot {
                    it in drawnCharactersList
                }
            )
        } else {

            _jdsaklaf.value = ThemedDrawerUiState.Error(
                errorMessage = R.string.draw_error
            )
        }
    }


    /**
     * Business Logic
     */

    fun drawNextCharacter() {
        funLogger("drawNextCharacter")
        viewModelScope.launch(Dispatchers.IO) {

            when (val state = dhaugof.value) {

                is ThemedDrawerUiState.Success -> {

                    val nextCharacter = state.availableCharacters.shuffled().first()

                    setDrawnElementsIds(
                        drawId = state.drawId,
                        drawnCharactersIds = getDrawnElementsIds(state.drawId) + ",${nextCharacter.id}"
                    )

                    refreshDrawState(state.drawId)
                }

                else -> {

                    return@launch
                }
            }
        }
    }

    fun finishDraw() {
        funLogger("finishDraw")
        viewModelScope.launch(Dispatchers.IO) {
            when (val state = dhaugof.value) {

                is ThemedDrawerUiState.Success -> {
                    finishDraw(state.drawId)
                    refreshDrawState(state.drawId)
                }

                else -> return@launch
            }
        }
    }

    fun stateNotStarted() {
        viewModelScope.launch(Dispatchers.IO) {
            _jdsaklaf.value = ThemedDrawerUiState.Loading
            _jdsaklaf.value = ThemedDrawerUiState.NotStarted(themes = themes.value)
        }
    }

    fun startNewDraw(theme: BingoTheme) {
        viewModelScope.launch(Dispatchers.IO) {

            val draw = Draw(
                themeId = theme.id,
                drawnCharactersIdList = "",
                drawCompleted = false
            )

            val drawId = createNewDraw(draw)

            refreshDrawState(drawId)
        }
    }

    fun getStringOfDrawnCharacters(): String {
        var drawnString = "*CONFERÊNCIA*\n"
        var count = 1

        when (val thisUiState = dhaugof.value) {
            is ThemedDrawerUiState.Success -> {
                for (character in thisUiState.drawnCharacters) {
                    drawnString = drawnString + "\n" +
                            "*$count* - ${character.name} (${character.id})"

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

    private suspend fun getDrawById(drawId: Long): Draw? {
        return drawRepository.getDrawById(drawId)
    }

    private suspend fun getLastDraw(): Draw? {
        return drawRepository.getLastDraw()
    }

    private suspend fun finishDraw(drawId: Long) {
        drawRepository.finishDraw(drawId)
    }

    private suspend fun createNewDraw(draw: Draw): Long {
        return drawRepository.createNewDraw(draw)
    }

    private suspend fun getDrawnElementsIds(drawId: Long): String {
        return drawRepository.getDrawnElementsIds(drawId)
    }

    private suspend fun setDrawnElementsIds(drawId: Long, drawnCharactersIds: String) {
        drawRepository.setDrawnElementsIds(drawId, drawnCharactersIds)
    }
}