package com.mert.countries.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mert.countries.common.Constants
import com.mert.countries.utils.SavedManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferenceModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            Constants.PREF_NAME, Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun provideSavedManager(preferences: SharedPreferences, gson: Gson) = SavedManager(preferences,gson)
}