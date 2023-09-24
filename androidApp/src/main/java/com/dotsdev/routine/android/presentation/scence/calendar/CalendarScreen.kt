package com.dotsdev.routine.android.presentation.scence.calendar

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute
import com.dotsdev.routine.android.ui.components.CalendarCardHeader
import com.dotsdev.routine.android.ui.components.CalendarTabHeader
import com.dotsdev.routine.android.ui.components.CalendarTabItem
import com.dotsdev.routine.android.ui.components.rememberCalendarTabContentScrollState
import com.dotsdev.routine.model.WeekDay

fun NavGraphBuilder.calendarScreens() {
    composable(AppRoute.calendarRoute) {
        CalendarScreen()
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    var selectedDay by rememberSaveable { mutableStateOf(WeekDay.Monday) }
    val calendarTabContentScrollState = rememberCalendarTabContentScrollState()
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Column {
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