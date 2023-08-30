package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.Draw
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.repository.CharacterRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.DrawRepository
import com.duscaranari.themedbingocardsgenerator.domain.repository.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val drawRepository: DrawRepository,
    private val themeRepository: ThemeRepository,
    private val characterRepository: CharacterRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _drawerUiState = MutableStateFlow<DrawerUiState>(DrawerUiState.Loading)
    val uiState = _drawerUiState.asStateFlow()

    init {
        checkSavedState()
    }


    /**
     * UI Logic
     */

    fun checkSavedState() {
        viewModelScope.launch(Dispatchers.IO) {
            val activeDraw = getActiveDraw()
            if (activeDraw != null) refreshDrawState(activeDraw.drawId) else startNewDraw()
        }
    }

    private suspend fun refreshDrawState(drawId: Long) {

        val draw = getDrawById(drawId)

        if (draw != null) {

            val theme = getThemeById(draw.themeId)
            val themeCharacters = getThemeCharacters(draw.themeId)

            if (theme == null || themeCharacters == null) {

                _drawerUiState.value = DrawerUiState.Error(
                    errorMessage = R.string.draw_error
                )
            } else {

                val drawnCharactersIdList = draw.drawnCharactersIdList.split(",")
                val drawnCharactersList = mutableListOf<Character>()

                for (id in drawnCharactersIdList) {
                    themeCharacters.find { it.characterId == id }?.let {
                        drawnCharactersList.add(it)
                    }
                }

                _drawerUiState.value = DrawerUiState.Success(
                    drawId = draw.drawId,
                    isFinished = draw.drawCompleted,
                    theme = theme,
                    themeCharacters = themeCharacters,
                    drawnCharacters = drawnCharactersList,
                    availableCharacters = themeCharacters.filterNot {
                        it in drawnCharactersList
                    }
                )
            }
        } else {

            _drawerUiState.value = DrawerUiState.Error(
                errorMessage = R.string.draw_error
            )
        }


    }

    private suspend fun startNewDraw() {

        val themeId = checkNotNull(savedStateHandle["themeId"]).toString()
        val theme = getThemeById(themeId)
        val themeCharacters = getThemeCharacters(themeId)

        if (theme == null || themeCharacters == null) {

            _drawerUiState.value = DrawerUiState.Error(
                errorMessage = R.string.draw_error
            )
        } else {

            val draw = Draw(
                themeId = themeId,
                drawnCharactersIdList = "",
                drawCompleted = false
            )

            val drawId = createNewDraw(draw)

            refreshDrawState(drawId)
        }
    }


    /**
     * Business Logic
     */

    fun drawNextCharacter() {

        viewModelScope.launch(Dispatchers.IO) {

            when (val state = uiState.value) {

                is DrawerUiState.Success -> {

                    val nextCharacter = state.availableCharacters.shuffled().first()

                    setDrawnElementsIds(
                        drawId = state.drawId,
                        drawnCharactersIds = getDrawnElementsIds(state.drawId) + ",${nextCharacter.characterId}"
                    )

                    if (state.availableCharacters.size - state.drawnCharacters.size == 1) {
                        finishDraw(state.drawId)
                    }

                    refreshDrawState(state.drawId)
                }

                else -> {

                    return@launch
                }
            }
        }
    }

    fun finishDraw() {

        viewModelScope.launch(Dispatchers.IO) {
            when (val state = uiState.value) {

                is DrawerUiState.Success -> {
                    finishDraw(state.drawId)
                    refreshDrawState(state.drawId)
                }

                else -> return@launch
            }
        }
    }


    /**
     * Repository Functions
     */

    private suspend fun getDrawById(drawId: Long): Draw? {
        return drawRepository.getDrawById(drawId)
    }

    private suspend fun getActiveDraw(): Draw? {
        return drawRepository.getActiveDraw()
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

    private suspend fun getThemeById(themeId: String): Theme? {
        return themeRepository.getThemeById(themeId)
    }

    private suspend fun getThemeCharacters(themeId: String): List<Character>? {
        return characterRepository.getThemeCharacters(themeId)
    }
}