package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.AppData
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme

@Database(
    entities =[AppData::class, Theme::class, Character::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDataDao(): AppDataDao
    abstract fun themeDao() : ThemeDao
    abstract fun characterDao() : CharacterDao
}