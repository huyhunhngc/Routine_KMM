package com.dotsdev.routine.android.presentation.scence.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotsdev.routine.domain.usecase.ThemeUseCase
import com.dotsdev.routine.model.preferences.AppThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeUseCase: ThemeUseCase
) : ViewModel() {
    val appTheme: StateFlow<AppThemeType> = themeUseCase.observeTheme().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            AppThemeType.FOLLOW_SYSTEM
        )

    fun setAppTheme(theme: AppThemeType) = viewModelScope.launch {
        themeUseCase.setAppTheme(theme)
    }
}