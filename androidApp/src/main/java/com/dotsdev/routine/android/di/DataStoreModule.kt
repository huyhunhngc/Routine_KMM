package com.dotsdev.routine.android.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.dotsdev.routine.domain.datastore.SettingDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideSettingDataStore(@ApplicationContext context: Context): SettingDataStore {
        val dataStore = PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("setting_datastore")
        }
        return SettingDataStore(dataStore)
    }
}