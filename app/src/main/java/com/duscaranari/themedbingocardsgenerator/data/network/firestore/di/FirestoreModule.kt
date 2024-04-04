package com.duscaranari.themedbingocardsgenerator.data.network.firestore.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirestoreModule {

    @Singleton
    @Provides
    fun provideFirestoreInstance() =
        Firebase.firestore
}