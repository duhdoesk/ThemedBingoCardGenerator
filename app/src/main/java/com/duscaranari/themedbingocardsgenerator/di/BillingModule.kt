package com.duscaranari.themedbingocardsgenerator.di

import android.app.Activity
import android.content.Context
import com.duscaranari.themedbingocardsgenerator.util.PurchaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BillingModule {

    @Singleton
    @Provides
    fun providePurchaseHelper(@ApplicationContext app: Context) : PurchaseHelper {
        return PurchaseHelper(app)
    }
}