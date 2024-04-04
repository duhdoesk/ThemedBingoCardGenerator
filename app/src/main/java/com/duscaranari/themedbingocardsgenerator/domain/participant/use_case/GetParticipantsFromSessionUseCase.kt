package com.duscaranari.themedbingocardsgenerator.domain.participant.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.ParticipantRepository
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import javax.inject.Inject

class GetParticipantsFromSessionUseCase @Inject constructor(private val participantRepository: ParticipantRepository) {

    operator fun invoke(sessionId: String) =
        participantRepository.getParticipantsFromSessionId(sessionId)
}