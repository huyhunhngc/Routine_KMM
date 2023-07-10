package com.dotsdev.routine.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotsdev.routine.domain.usecase.ThemeUseCase
import com.dotsdev.routine.model.preferences.AppThemeType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RoutineMainViewModel @Inject constructor(
    themeUseCase: ThemeUseCase
): ViewModel() {
    val appTheme: StateFlow<AppThemeType> = themeUseCase.observeTheme().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        AppThemeType.FOLLOW_SYSTEM
    )
}