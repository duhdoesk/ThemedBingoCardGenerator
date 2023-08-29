package com.duscaranari.themedbingocardsgenerator.presentation.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.collectAsState
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
        setupUiState()
    }

    private fun setupUiState() {
        viewModelScope.launch(Dispatchers.IO) {
            val activeDraw = getActiveDraw()
            if (activeDraw != null) resumeActiveDraw(activeDraw) else startNewDraw()
        }
    }

    private suspend fun getActiveDraw(): Draw? {
        return drawRepository.getActiveDraw()
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

    private suspend fun createNewDraw(draw: Draw): Long {
        return drawRepository.createNewDraw(draw)
    }

    private suspend fun getThemeById(themeId: String): Theme? {
        return themeRepository.getThemeById(themeId)
    }

    private suspend fun getThemeCharacters(themeId: String): List<Character>? {
        return characterRepository.getThemeCharacters(themeId)
    }
}