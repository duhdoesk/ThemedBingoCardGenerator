package com.duscaranari.themedbingocardsgenerator.domain.participant.use_case

import com.duscaranari.themedbingocardsgenerator.R
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.ParticipantDTO
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.ParticipantRepository
import javax.inject.Inject

class JoinSessionUseCase @Inject constructor(private val participantRepository: ParticipantRepository) {

    operator fun invoke(
        session: SessionDTO,
        participantDTO: ParticipantDTO,
        password: String?
    ): JoinResult {
        return if (!session.locked || session.password == password) {
                participantRepository.joinSession(
                    sessionId = session.id,
                    participantDTO = participantDTO
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