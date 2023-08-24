package com.duscaranari.themedbingocardsgenerator.domain.repository

import com.duscaranari.themedbingocardsgenerator.data.UserDao
import com.duscaranari.themedbingocardsgenerator.domain.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUser(userId: String) =
        userDao.getUser(userId)

    suspend fun insertUser(user: User) =
        userDao.insertUser(user)
}