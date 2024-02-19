package com.duscaranari.themedbingocardsgenerator.domain.user.use_case

import com.duscaranari.themedbingocardsgenerator.domain.user.model.User
import com.duscaranari.themedbingocardsgenerator.domain.user.repository.UserRepository
import javax.inject.Inject

class SetUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) =
        userRepository.insertUser(user)
}