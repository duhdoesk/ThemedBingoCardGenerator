package com.duscaranari.themedbingocardsgenerator.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.duscaranari.themedbingocardsgenerator.domain.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.model.AppData
import com.duscaranari.themedbingocardsgenerator.domain.model.ClassicDraw
import com.duscaranari.themedbingocardsgenerator.domain.model.Draw
import com.duscaranari.themedbingocardsgenerator.domain.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.model.User

@Database(
    version = 2,
    entities = [
        AppData::class,
        Theme::class,
        Character::class,
        User::class,
        Draw::class,
        ClassicDraw::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDataDao(): AppDataDao
    abstract fun themeDao(): ThemeDao
    abstract fun characterDao(): CharacterDao
    abstract fun userDao(): UserDao
    abstract fun drawDao(): DrawDao
    abstract fun classicDrawDao(): ClassicDrawDao
}