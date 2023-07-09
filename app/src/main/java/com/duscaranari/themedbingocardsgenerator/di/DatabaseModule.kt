package com.duscaranari.themedbingocardsgenerator.di

import android.content.Context
import androidx.room.Room
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
            .createFromAsset("Themed Bingo Card Generator.db")
            .build()

    @Singleton
    @Provides
    fun providesThemeDao(appDatabase: AppDatabase) : ThemeDao =
        appDatabase.themeDao()

    @Singleton
    @Provides
    fun providesCharacterDao(appDatabase: AppDatabase) : CharacterDao =
        appDatabase.characterDao()
}