package com.duscaranari.themedbingocardsgenerator.di

import android.content.Context
import androidx.room.Room
import com.duscaranari.themedbingocardsgenerator.data.AppDataDao
import com.duscaranari.themedbingocardsgenerator.data.AppDatabase
import com.duscaranari.themedbingocardsgenerator.data.CharacterDao
import com.duscaranari.themedbingocardsgenerator.data.ThemeDao
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
            .build()

    @Singleton
    @Provides
    fun provideAppDataDao(appDatabase: AppDatabase) : AppDataDao =
        appDatabase.appDataDao()

    @Singleton
    @Provides
    fun provideThemeDao(appDatabase: AppDatabase) : ThemeDao =
        appDatabase.themeDao()

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase) : CharacterDao =
        appDatabase.characterDao()
}