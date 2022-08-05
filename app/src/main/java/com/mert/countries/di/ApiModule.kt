package com.mert.countries.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mert.countries.common.Constants
import com.mert.countries.data.remote.CityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesBaseUrl() = "https://wft-geo-db.p.rapidapi.com/v1/geo/"

    @Singleton
    @Provides
    fun provideRestApiService(retrofit: Retrofit): CityService {
        return retrofit.create(CityService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor { message -> Timber.d(message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val urlWithParams = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key", Constants.API_KEY)
                .build()
            val request: Request = chain.request()
                .newBuilder()
                .addHeader("X-RapidAPI-Key", Constants.API_KEY)
                .url(urlWithParams)
                .build();
            chain.proceed(request);
        }.build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}