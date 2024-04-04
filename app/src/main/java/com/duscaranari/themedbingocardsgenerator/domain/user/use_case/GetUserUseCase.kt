package com.duscaranari.themedbingocardsgenerator.domain.user.use_case

import com.duscaranari.themedbingocardsgenerator.data.local.repository.LocalUserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val localUserRepository: LocalUserRepository) {

    suspend operator fun invoke(userId: String) =
        localUserRepository.getUser(userId)
}