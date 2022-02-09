package com.example.animalfarm_kotlin.di

import com.example.animalfarm_kotlin.network.AnimalsApi
import com.example.animalfarm_kotlin.network.AnimalsApi.Companion.BASE_URL
import com.example.animalfarm_kotlin.network.AnimalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimalsApi(): AnimalsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalsApi::class.java)

    @Provides
    @Singleton
    fun provideAnimalsRepository(animalsApi: AnimalsApi): AnimalsRepository =
        AnimalsRepository(animalsApi)

}