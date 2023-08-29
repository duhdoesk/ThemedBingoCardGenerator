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

    private fun checkSavedState() {
        viewModelScope.launch(Dispatchers.IO) {
            val activeDraw = getActiveDraw()
            if (activeDraw != null) resumeActiveDraw(activeDraw) else startNewDraw()
        }
    }

    private suspend fun resumeActiveDraw(activeDraw: Draw) {

        val theme = getThemeById(activeDraw.themeId)
        val themeCharacters = getThemeCharacters(activeDraw.themeId)

        if (theme == null || themeCharacters == null) {

            _drawerUiState.value = DrawerUiState.Error(
                errorMessage = R.string.draw_error
            )
        } else {

            val drawnCharactersIdList = activeDraw.drawnCharactersIdList.split(",")

            _drawerUiState.value = DrawerUiState.Success(
                drawId = activeDraw.drawId,
                isFinished = activeDraw.drawCompleted,
                theme = theme,
                themeCharacters = themeCharacters,
                drawnCharacters = themeCharacters.filter {
                    it.characterId in drawnCharactersIdList
                },
                availableCharacters = themeCharacters.filterNot {
                    it.characterId in drawnCharactersIdList
                }
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

            _drawerUiState.value = DrawerUiState.Success(
                drawId = drawId,
                isFinished = false,
                theme = theme,
                themeCharacters = themeCharacters,
                availableCharacters = themeCharacters,
                drawnCharacters = null
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

                    val state = (uiState as DrawerUiState.Success)
                    val nextCharacter = state.availableCharacters?.shuffled()?.first()

                    setDrawnElementsIds(
                        drawId = state.drawId,
                        drawnCharactersIds = getDrawnElementsIds(state.drawId) + ",${nextCharacter?.characterId}"
                    )

                    getActiveDraw()?.let {
                        resumeActiveDraw(it)
                    }
                }
                else -> {

                    return@launch
                }
            }
        }
    }


    /**
     * Repository Functions
     */

    private suspend fun getActiveDraw(): Draw? {
        return drawRepository.getActiveDraw()
    }

    private suspend fun finishDraw(drawId: Long) {
        drawRepository.finishDraw(drawId)
    }

    private suspend fun createNewDraw(draw: Draw): Long {
        return drawRepository.createNewDraw(draw)
    }

    private suspend fun getDrawThemeId(drawId: Long): String {
        return drawRepository.getDrawThemeId(drawId)
    }

    private suspend fun setDrawThemeId(drawId: Long, themeId: String)  {
        drawRepository.setDrawThemeId(drawId, themeId)
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