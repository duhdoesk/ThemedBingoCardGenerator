package com.duscaranari.themedbingocardsgenerator.domain.session.use_case

import com.duscaranari.themedbingocardsgenerator.data.network.firestore.repository.SessionRepository
import com.duscaranari.themedbingocardsgenerator.domain.session.model.Session
import javax.inject.Inject

class CreateNewSessionUseCase @Inject constructor(private val sessionRepository: SessionRepository) {

    operator fun invoke(session: Session) =
        sessionRepository.createNewSession(session)
}