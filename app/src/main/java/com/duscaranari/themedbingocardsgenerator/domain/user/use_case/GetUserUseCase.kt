package com.duscaranari.themedbingocardsgenerator.domain.user.use_case

import com.duscaranari.themedbingocardsgenerator.domain.user.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(userId: String) =
        userRepository.getUser(userId)
}