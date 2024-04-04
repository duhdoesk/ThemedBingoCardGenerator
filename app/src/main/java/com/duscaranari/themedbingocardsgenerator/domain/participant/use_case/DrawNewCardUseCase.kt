package com.duscaranari.themedbingocardsgenerator.domain.participant.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.ParticipantRepository
import com.duscaranari.themedbingocardsgenerator.domain.character.model.BingoCharacter
import javax.inject.Inject

class DrawNewCardUseCase @Inject constructor(private val participantRepository: ParticipantRepository) {

    operator fun invoke(
        sessionId: String,
        participantId: String,
        card: List<BingoCharacter>
    ) =
        participantRepository.drawNewCard(
            sessionId = sessionId, participantId = participantId, card = card
        )
}