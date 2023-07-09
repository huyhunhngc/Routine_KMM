package com.dotsdev.routine.domain.usecase

import com.dotsdev.routine.theme.AppTheme
import kotlinx.coroutines.flow.Flow

interface ThemeUseCase {
    suspend fun observeTheme(): Flow<AppTheme>
}

class ThemeUseCaseImpl: ThemeUseCase {
    override suspend fun observeTheme(): Flow<AppTheme> {
        TODO("Not yet implemented")
    }

}