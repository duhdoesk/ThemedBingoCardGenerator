package com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.CreateNewSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllBingoThemesUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Host
import com.duscaranari.themedbingocardsgenerator.ui.presentation.create_session.state.CreateSessionUiState
import com.duscaranari.themedbingocardsgenerator.util.auth.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CreateSessionViewModel @Inject constructor(
    private val createNewSessionUseCase: CreateNewSessionUseCase,
    getAllBingoThemesUseCase: GetAllBingoThemesUseCase,
    authHelper: AuthHelper
) : ViewModel() {

    val user = authHelper.getSignedInUser()!!

    private val _limitOfWinners = mutableFloatStateOf(1f)

    private val _uiState = MutableStateFlow(CreateSessionUiState())
    val uiState = _uiState
        .onEach {
            _isFormOk.update {
                !((_uiState.value.name.length < 4 || _uiState.value.themeId == "") ||
                        (_uiState.value.locked && _uiState.value.password.length < 4))
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            CreateSessionUiState()
        )

    private val _isFormOk = MutableStateFlow(false)
    val isFormOk = _isFormOk.asStateFlow()

    private val _themes = getAllBingoThemesUseCase.invoke()
    val themes = _themes
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun onUpdateName(name: String) {
        val errors = mutableListOf<String>()

        if (name.length < 4) {
            errors.add("The name must be at least 4 characters.")
        }

        _uiState.value = _uiState.value.copy(
            name = name,
            nameErrors = errors
        )
    }

    fun onUpdateLocked(locked: Boolean) {
        _uiState.value = _uiState.value.copy(
            locked = locked,
            password = ""
        )
    }

    fun onUpdatePassword(password: String) {
        val errors = mutableListOf<String>()

        if (password.length < 4) {
            errors.add("The password must be at least 4 characters.")
        }

        _uiState.value = _uiState.value.copy(
            password = password,
            passwordErrors = errors
        )
    }

    fun onUpdateThemeId(themeId: String) {
        _uiState.value = _uiState.value.copy(
            themeId = themeId
        )
    }

    fun onUpdateLimitOfWinners(limit: Float) {
        _limitOfWinners.floatValue = limit

        Log.d("Slider", _limitOfWinners.floatValue.roundToInt().toString())
    }

    fun onCreateNewSession(): String {
        uiState.value.run {
            return createNewSessionUseCase.invoke(
                SessionDTO(
                    name = name,
                    state = "NOT_STARTED",
                    locked = locked,
                    password = password,
                    themeId = themeId,
                    listOfDrawnCharactersIds = emptyList(),
                    limitOfWinners = _limitOfWinners.floatValue.roundToInt(),
                    host = user.run {
                        Host(
                            id = id,
                            name = name ?: "",
                            picture = picture ?: ""
                        )
                    }
                )
            )
        }
    }
}