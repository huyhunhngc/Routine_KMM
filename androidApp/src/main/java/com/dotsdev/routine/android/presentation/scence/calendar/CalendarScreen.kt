package com.dotsdev.routine.android.presentation.scence.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute
import com.dotsdev.routine.android.ui.components.CalendarCardHeader

fun NavGraphBuilder.calendarScreens() {
    composable(AppRoute.calendarRoute) {
        CalendarScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarScreen(viewModel: CalendarViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {

            LazyColumn(
                state = listState
            ) {
                for (item in 0..50) {
                    if (item % 10 == 0) {
                        stickyHeader {
                            CalendarCardHeader(isAccepted = true, showDate = true)
                        }
                    } else {
                        item {
                            CalendarCardHeader(isAccepted = item % 2 == 0, showDate = false)
                        }
                    }
                }
            }
        }
    }
}