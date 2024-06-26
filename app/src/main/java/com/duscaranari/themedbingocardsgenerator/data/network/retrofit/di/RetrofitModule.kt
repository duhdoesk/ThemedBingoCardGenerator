package com.duscaranari.themedbingocardsgenerator.data.network.retrofit.di

import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.ApiHelper
import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.ApiHelperImpl
import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.ApiService
import com.duscaranari.themedbingocardsgenerator.data.network.retrofit.util.DomainModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://duhdoesk.github.io/ThemedBingoCardGenerator/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Singleton
    @Provides
    fun provideDomainModelMapper() : DomainModelMapper = DomainModelMapper()
}