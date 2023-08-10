package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duscaranari.themedbingocardsgenerator.domain.model.AppData


@Dao
interface AppDataDao {

//    QUERY

    @Query("SELECT * from data_table")
    suspend fun getAppData(): AppData


//    DELETE

    @Query("DELETE from data_table")
    suspend fun clearAppDataTable()


//    INSERT

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppData(appData: AppData)
}