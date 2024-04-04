package com.duscaranari.themedbingocardsgenerator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.data.local.model.LocalUser

@Dao
interface UserDao {

    @Query("SELECT * from user_table where user_id=:userId")
    suspend fun getUser(userId: String) : LocalUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(localUser: LocalUser)
}