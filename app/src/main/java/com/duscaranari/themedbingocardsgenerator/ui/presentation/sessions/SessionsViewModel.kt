package com.duscaranari.themedbingocardsgenerator.ui.presentation.sessions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.GetSessionsUseCase
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.JoinResult
import com.duscaranari.themedbingocardsgenerator.domain.session.use_case.JoinSessionUseCase
import com.duscaranari.themedbingocardsgenerator.domain.theme.use_case.GetAllBingoThemesUseCase
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.NetworkUser
import com.duscaranari.themedbingocardsgenerator.util.auth.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionsViewModel @Inject constructor(
    getSessionsUseCase: GetSessionsUseCase,
    private val joinSessionUseCase: JoinSessionUseCase,
    getAllBingoThemesUseCase: GetAllBingoThemesUseCase
) : ViewModel() {

    private val _sessions = getSessionsUseCase.invoke()
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
        session: SessionDTO,
        userData: UserData?,
        password: String? = null
    ): JoinResult {

        if (userData != null) {
            val networkUser = NetworkUser(
                id = userData.id,
                name = userData.name ?: "",
                picture = userData.picture ?: ""
            )

            return joinSessionUseCase.invoke(
                session = session,
                networkUser = networkUser,
                password = password
            )
        }

        else return JoinResult.Error(
            message = R.string.login_needed
        )
    }
}