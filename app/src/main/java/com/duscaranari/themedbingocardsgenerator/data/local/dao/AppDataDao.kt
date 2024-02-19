package com.duscaranari.themedbingocardsgenerator.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.app_data.model.AppData


@Dao
interface AppDataDao {

//    QUERY

    @Query("SELECT * from data_table")
    suspend fun getAppData(): AppData?


//    INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppData(appData: AppData)
}