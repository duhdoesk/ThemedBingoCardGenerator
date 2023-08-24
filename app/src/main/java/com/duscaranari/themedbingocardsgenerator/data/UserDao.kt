package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.model.User

@Dao
interface UserDao {

    @Query("SELECT * from user_table where user_id=:userId")
    suspend fun getUser(userId: String) : User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
}