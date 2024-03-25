package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import javax.inject.Inject

class DrawNextCharacterUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(sessionId: String, characterId: String) =
        sessionRepository.drawNextCharacter(sessionId, characterId)
}