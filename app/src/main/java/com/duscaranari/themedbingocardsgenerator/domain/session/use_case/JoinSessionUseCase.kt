package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import com.duscaranari.themedbingocardsgenerator.domain.user.model.Participant
import javax.inject.Inject

class JoinSessionUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(
        session: Session,
        participant: Participant,
        password: String?
    ): JoinResult {
        return if (!session.locked || session.password == password) {
                sessionRepository.joinSession(
                    sessionId = session.id,
                    participant = participant
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