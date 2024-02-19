package com.duscaranari.themedbingocardsgenerator.domain.user.repository

import com.duscaranari.themedbingocardsgenerator.data.local.dao.UserDao
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUser(userId: String) =
        userDao.getUser(userId)

    suspend fun insertUser(user: User) =
        userDao.insertUser(user)
}