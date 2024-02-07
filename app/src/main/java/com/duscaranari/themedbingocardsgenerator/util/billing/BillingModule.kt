package com.duscaranari.themedbingocardsgenerator.util.billing

import android.content.Context
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
    fun provideBillingHelper(@ApplicationContext context: Context) : BillingHelper {
        return BillingHelper(context)
    }
}