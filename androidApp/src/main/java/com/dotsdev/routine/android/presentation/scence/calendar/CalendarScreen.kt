package com.dotsdev.routine.android.presentation.scence.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute
import com.dotsdev.routine.android.ui.components.CalendarEventItem

fun NavGraphBuilder.calendarScreens() {
    composable(AppRoute.calendarRoute) {
        CalendarScreen()
    }
}

@Composable
fun CalendarScreen(viewModel: CalendarViewModel = hiltViewModel()) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CalendarEventItem()
        }
    }
}