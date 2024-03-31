package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import javax.inject.Inject

class AddWinnerUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(sessionId: String, winnerId: String) =
        sessionRepository.addWinner(sessionId = sessionId, winnerId = winnerId)
}