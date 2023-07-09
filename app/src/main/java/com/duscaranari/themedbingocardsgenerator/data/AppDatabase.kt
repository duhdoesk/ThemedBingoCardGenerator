package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duscaranari.themedbingocardsgenerator.model.Character
import com.duscaranari.themedbingocardsgenerator.model.Theme

@Database(
    entities =[Theme::class, Character::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun themeDao() : ThemeDao
    abstract fun characterDao() : CharacterDao
}