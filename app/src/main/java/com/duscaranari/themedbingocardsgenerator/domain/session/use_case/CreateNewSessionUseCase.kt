package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import com.duscaranari.themedbingocardsgenerator.data.network.firestore.model.SessionDTO
import javax.inject.Inject

class CreateNewSessionUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(session: SessionDTO): String =
        sessionRepository.createNewSession(session)
}