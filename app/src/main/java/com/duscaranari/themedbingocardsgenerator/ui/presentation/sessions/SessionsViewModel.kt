package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetNotStartedSessionsUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.JoinResult
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.JoinSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllBingoThemesUseCase
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Participant
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(
    getNotStartedSessionsUseCase: GetNotStartedSessionsUseCase,
    private val joinSessionUseCase: JoinSessionUseCase,
    getAllBingoThemesUseCase: GetAllBingoThemesUseCase
) : ViewModel() {

    private val _sessions = getNotStartedSessionsUseCase.invoke()
    val sessions = _sessions
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    private val _themes = getAllBingoThemesUseCase.invoke()
    val themes = _themes.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    fun onJoinSession(
        session: Session,
        userData: UserData?,
        password: String? = null
    ): JoinResult {

        if (userData != null) {
            val participant = Participant(
                id = userData.id,
                name = userData.name ?: "",
                picture = userData.picture ?: ""
            )

            return joinSessionUseCase.invoke(
                session = session,
                participant = participant,
                password = password
            )
        }

        else return JoinResult.Error(
            message = R.string.login_needed
        )
    }
}