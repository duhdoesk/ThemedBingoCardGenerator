package com.duscaranari.themedbingocardsgenerator.util.auth

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthHelper(@ApplicationContext context: Context) : AuthHelper {
        return AuthHelper(context)
    }
}