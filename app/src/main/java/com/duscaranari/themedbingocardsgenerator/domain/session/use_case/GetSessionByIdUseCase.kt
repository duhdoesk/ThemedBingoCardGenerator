package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import javax.inject.Inject

class GetSessionByIdUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(id: String) =
        sessionRepository.getSessionById(id)
}