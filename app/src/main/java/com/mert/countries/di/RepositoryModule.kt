package com.mert.countries.di

import com.mert.countries.data.remote.CityService
import com.mert.countries.data.repository.CountryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(citiesAPI: CityService): CountryRepositoryImpl {
        return CountryRepositoryImpl(citiesAPI)
    }
}