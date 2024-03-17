package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.NetworkUser
import javax.inject.Inject

class JoinSessionUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(
        session: SessionDTO,
        networkUser: NetworkUser,
        password: String?
    ): JoinResult {
        return if (!session.locked || session.password == password) {
                sessionRepository.joinSession(
                    sessionId = session.id,
                    networkUser = networkUser
                )
                JoinResult.Success
            }

            else JoinResult.Error(message = R.string.incorrect_password)
    }
}

sealed class JoinResult {
    data object Success: JoinResult()
    data class Error(val message: Int): JoinResult()
}