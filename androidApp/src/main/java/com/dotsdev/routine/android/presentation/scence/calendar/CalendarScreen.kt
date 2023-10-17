package com.dotsdev.routine.android.presentation.scence.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute
import com.dotsdev.routine.ui.components.CalendarCardHeader
import com.dotsdev.routine.android.ui.section.CalendarTabHeader
import com.dotsdev.routine.android.ui.section.CalendarTabItem
import com.dotsdev.routine.android.ui.section.rememberCalendarTabContentScrollState
import com.dotsdev.routine.model.WeekDay

fun NavGraphBuilder.calendarScreens() {
    composable(AppRoute.calendarRoute) {
        CalendarScreen()
    }
}

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    CalendarScreen(uiState)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CalendarScreen(
    uiState: CalendarViewModel.CalendarUiState,
) {
    val listState = rememberLazyListState()
    var selectedDay by rememberSaveable { mutableStateOf(WeekDay.Monday) }
    val calendarTabContentScrollState = rememberCalendarTabContentScrollState()
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column(
                modifier = Modifier
                    .nestedScroll(calendarTabContentScrollState.nestedScrollConnection)
            ) {
                CalendarTabHeader(
                    modifier = Modifier.padding(8.dp),
                    tabState = calendarTabContentScrollState.tabScrollState,
                    selectedTabIndex = selectedDay.index - 1,
                ) {
                    WeekDay.values().toMutableList().sortedBy { it.index }.forEach { day ->
                        CalendarTabItem(
                            day = day,
                            selected = day == selectedDay,
                            onClick = {
                                selectedDay = day
                            },
                            scrollState = calendarTabContentScrollState.tabScrollState,
                            modifier = Modifier,
                        )
                    }
                }
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
}