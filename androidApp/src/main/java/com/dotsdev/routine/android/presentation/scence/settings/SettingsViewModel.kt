package com.dotsdev.routine.android.presentation.scence.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotsdev.routine.model.preferences.AppThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    val appTheme: MutableStateFlow<AppThemeType> = MutableStateFlow(AppThemeType.FOLLOW_SYSTEM)

    fun setAppTheme(theme: AppThemeType) = viewModelScope.launch {
        appTheme.update { theme }
    }
}