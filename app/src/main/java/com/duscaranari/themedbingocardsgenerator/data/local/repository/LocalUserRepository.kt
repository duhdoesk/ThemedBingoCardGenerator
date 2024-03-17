package com.duscaranari.themedbingocardsgenerator.data.local.repository

import com.duscaranari.themedbingocardsgenerator.data.local.dao.UserDao
import com.duscaranari.themedbingocardsgenerator.data.local.model.LocalUser
import javax.inject.Inject

class LocalUserRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun getUser(userId: String) =
        userDao.getUser(userId)

    suspend fun insertUser(localUser: LocalUser) =
        userDao.insertUser(localUser)
}