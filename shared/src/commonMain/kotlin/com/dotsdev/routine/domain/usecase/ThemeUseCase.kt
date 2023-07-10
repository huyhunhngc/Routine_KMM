package com.dotsdev.routine.domain.usecase

import com.dotsdev.routine.domain.datastore.SettingDataStore
import com.dotsdev.routine.model.preferences.AppThemeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeUseCase(
    private val settingDataStore: SettingDataStore
) {
    fun observeTheme(): Flow<AppThemeType> {
        return settingDataStore.getInt(APP_THEME).map {
            AppThemeType.values()[it ?: 0]
        }
    }

    suspend fun setAppTheme(appTheme: AppThemeType) {
        settingDataStore.set(APP_THEME, appTheme.ordinal)
    }

    companion object {
        private const val APP_THEME = "app_theme"
    }
}