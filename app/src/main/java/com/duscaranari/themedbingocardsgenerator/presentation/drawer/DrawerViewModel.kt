package com.duscaranari.themedbingocardsgenerator.presentation.drawer

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
    private val characterRepository: CharacterRepository
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
            when (val lastDraw = getLastDraw()) {
                null -> _drawerUiState.value = DrawerUiState.NotStarted(themes = getAllThemes())
                else -> refreshDrawState(lastDraw.drawId)
            }
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

    fun stateNotStarted() {
        viewModelScope.launch(Dispatchers.IO) {
            _drawerUiState.value = DrawerUiState.NotStarted(themes = getAllThemes())
        }
    }

    fun startNewDraw(themeId: String) {

        viewModelScope.launch(Dispatchers.IO) {

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

    private suspend fun getAllThemes(): List<Theme> {
        return themeRepository.getAllThemes()
    }

    private suspend fun getThemeById(themeId: String): Theme? {
        return themeRepository.getThemeById(themeId)
    }

    private suspend fun getThemeCharacters(themeId: String): List<Character>? {
        return characterRepository.getThemeCharacters(themeId)
    }
}