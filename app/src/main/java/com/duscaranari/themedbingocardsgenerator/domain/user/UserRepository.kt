package com.duscaranari.themedbingocardsgenerator.domain.user

import com.duscaranari.themedbingocardsgenerator.data.local.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUser(userId: String) =
        userDao.getUser(userId)

    suspend fun insertUser(user: User) =
        userDao.insertUser(user)
}