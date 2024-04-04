package com.duscaranari.themedbingocardsgenerator.domain.user.use_case

import com.duscaranari.themedbingocardsgenerator.data.local.model.LocalUser
import com.duscaranari.themedbingocardsgenerator.data.local.repository.LocalUserRepository
import javax.inject.Inject

class SetUserUseCase @Inject constructor(private val localUserRepository: LocalUserRepository) {

    suspend operator fun invoke(localUser: LocalUser) =
        localUserRepository.insertUser(localUser)
}