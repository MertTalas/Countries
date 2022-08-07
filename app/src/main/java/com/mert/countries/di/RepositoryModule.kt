package com.mert.countries.di

import com.mert.countries.data.remote.CityService
import com.mert.countries.data.repository.CountryRepositoryImpl
import com.mert.countries.domain.repository.CountryRepository
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
    fun provideCountryRepository(cityAPI: CityService): CountryRepository {
        return CountryRepositoryImpl(cityAPI)
    }
}