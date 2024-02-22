package com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import com.duscaranari.themedbingocardsgenerator.domain.character.use_case.GetCharactersFromThemeIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.BingoTheme
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetBingoThemeByIdUseCase
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.model.Draw
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case.CreateNewThemedDrawUseCase
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case.FinishThemedDrawUseCase
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case.GetLastThemedDrawUseCase
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case.GetThemedDrawnElementsIdsUseCase
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.use_case.SetThemedDrawnElementsIdsUseCase
import com.duscaranari.themedbingocardsgenerator.ui.presentation.drawer.themed.state.ThemedDrawerUiState
import com.duscaranari.themedbingocardsgenerator.util.funLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val getLastThemedDrawUseCase: GetLastThemedDrawUseCase,
    private val finishThemedDrawUseCase: FinishThemedDrawUseCase,
    private val createNewThemedDrawUseCase: CreateNewThemedDrawUseCase,
    private val getThemedDrawnElementsIdsUseCase: GetThemedDrawnElementsIdsUseCase,
    private val setThemedDrawnElementsIdsUseCase: SetThemedDrawnElementsIdsUseCase,
    getBingoThemeByIdUseCase: GetBingoThemeByIdUseCase,
    getCharactersFromThemeIdUseCase: GetCharactersFromThemeIdUseCase
) : ViewModel() {

    private val _draw =
        MutableStateFlow<Draw?>(null)

    private val _themeId = when (_draw.value) {
        null -> ""
        else -> _draw.value!!.themeId
    }

    private val _theme = getBingoThemeByIdUseCase
        .invoke(_themeId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    private val _characters = getCharactersFromThemeIdUseCase
        .invoke(_themeId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    val uiState = combine(
        _draw,
        _theme,
        _characters
    ) { draw, theme, characters ->
        setUiState(draw, theme, characters)
    }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            ThemedDrawerUiState.Loading
        )

    init {
        refreshDrawState()
    }

    private fun setUiState(
        draw: Draw?, theme: BingoTheme?, characters: List<BingoCharacter>
    ) {
        when (draw) {
            null ->
                ThemedDrawerUiState.NotStarted

            else -> {
                when (theme) {
                    null ->
                        ThemedDrawerUiState.Error(errorMessage = R.string.draw_error)

                    else -> {
                        ThemedDrawerUiState.Success(
                            drawId = draw.drawId,
                            isFinished = draw.drawCompleted,
                            theme = theme,
                            characters = characters,
                            drawnCharacters = characters,
                            availableCharacters = characters
                        )
                    }
                }
            }
        }
    }

    fun refreshDrawState() {
        viewModelScope.launch(Dispatchers.IO) {
            _draw.update { getLastThemedDrawUseCase.invoke() }
        }
    }

    fun onDrawNextCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val state = uiState.value) {

                is ThemedDrawerUiState.Success -> {
                    val nextCharacter = state.availableCharacters.shuffled().first()

                    setThemedDrawnElementsIdsUseCase(
                        drawId = state.drawId,
                        drawnCharactersIds = getThemedDrawnElementsIdsUseCase.invoke(state.drawId) + ",${nextCharacter.id}"
                    )

                    refreshDrawState()
                }

                else ->
                    return@launch
            }
        }
    }

    fun onFinishDraw() {
        funLogger("finishDraw")
        viewModelScope.launch(Dispatchers.IO) {
            when (val state = uiState.value) {

                is ThemedDrawerUiState.Success -> {
                    finishThemedDrawUseCase.invoke(state.drawId)
                    refreshDrawState()
                }

                else ->
                    return@launch
            }
        }
    }

    fun stateNotStarted() {
        viewModelScope.launch(Dispatchers.IO) {
            _draw.update { null }
        }
    }

    fun onStartNewDraw(theme: BingoTheme) {
        viewModelScope.launch(Dispatchers.IO) {

            val draw = Draw(
                themeId = theme.id,
                drawnCharactersIdList = "",
                drawCompleted = false
            )

            createNewThemedDrawUseCase.invoke(draw)
            refreshDrawState()
        }
    }

    fun getStringOfDrawnCharacters(): String {
        var drawnString = "*CONFERÊNCIA*\n"
        var count = 1

        if (uiState.value is ThemedDrawerUiState.Success) {
            for (character in (uiState.value as ThemedDrawerUiState.Success).drawnCharacters) {
                drawnString = drawnString + "\n" +
                        "*$count* - ${character.name} (${character.id})"

                count += 1
            }
        }

        return drawnString
    }
}