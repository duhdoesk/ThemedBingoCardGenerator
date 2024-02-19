package com.duscaranari.themedbingocardsgenerator.data.local.di

import android.content.Context
import androidx.room.Room
import com.duscaranari.themedbingocardsgenerator.data.local.dao.AppDataDao
import com.duscaranari.themedbingocardsgenerator.data.local.AppDatabase
import com.duscaranari.themedbingocardsgenerator.data.local.dao.CharacterDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.ClassicDrawDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.DrawDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.ThemeDao
import com.duscaranari.themedbingocardsgenerator.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                "appDatabase"
            )
            .createFromAsset("db.db")
            .addMigrations(MIGRATION_1_2)
            .build()

    @Singleton
    @Provides
    fun provideAppDataDao(appDatabase: AppDatabase): AppDataDao =
        appDatabase.appDataDao()

    @Singleton
    @Provides
    fun provideThemeDao(appDatabase: AppDatabase): ThemeDao =
        appDatabase.themeDao()

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao =
        appDatabase.characterDao()

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

    @Singleton
    @Provides
    fun provideDrawDao(appDatabase: AppDatabase): DrawDao =
        appDatabase.drawDao()

    @Singleton
    @Provides
    fun provideClassicDrawDao(appDatabase: AppDatabase): ClassicDrawDao =
        appDatabase.classicDrawDao()
}