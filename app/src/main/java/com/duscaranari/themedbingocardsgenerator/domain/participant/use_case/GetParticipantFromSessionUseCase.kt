package com.duscaranari.themedbingocardsgenerator.domain.participant.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.ParticipantRepository
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import javax.inject.Inject

class GetParticipantFromSessionUseCase @Inject constructor(private val participantRepository: ParticipantRepository) {

    operator fun invoke(
        sessionId: String,
        participantId: String
    ) = participantRepository.getParticipantFromSessionId(
        sessionId = sessionId, participantId = participantId
    )
}