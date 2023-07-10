package com.dotsdev.routine.android.di

import com.dotsdev.routine.domain.datastore.SettingDataStore
import com.dotsdev.routine.domain.usecase.ThemeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideThemeUseCase(
        dataStore: SettingDataStore
    ): ThemeUseCase {
        return ThemeUseCase(dataStore)
    }
}
