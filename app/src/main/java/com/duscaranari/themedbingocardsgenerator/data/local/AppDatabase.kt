package com.duscaranari.themedbingocardsgenerator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duscaranari.themedbingocardsgenerator.data.local.dao.AppDataDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.CharacterDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.ClassicDrawDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.DrawDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.ThemeDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.UserDao
import com.duscaranari.themedbingocardsgenerator.domain.character.model.Character
import com.duscaranari.themedbingocardsgenerator.domain.app_data.model.AppData
import com.duscaranari.themedbingocardsgenerator.domain.classic_draw.model.ClassicDraw
import com.duscaranari.themedbingocardsgenerator.domain.themed_draw.model.Draw
import com.duscaranari.themedbingocardsgenerator.domain.theme.model.Theme
import com.duscaranari.themedbingocardsgenerator.domain.user.model.User

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