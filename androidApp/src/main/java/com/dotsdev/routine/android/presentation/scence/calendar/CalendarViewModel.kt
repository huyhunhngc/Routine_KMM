package com.dotsdev.routine.android.presentation.scence.calendar

import androidx.lifecycle.ViewModel
import com.dotsdev.routine.android.presentation.scence.task.TaskViewModel
import com.dotsdev.routine.android.util.uiState
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.model.WeekDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(): ViewModel() {
    sealed interface CalendarUiState {
        object Empty : CalendarUiState
        data class ListTimetable(
            val timetableListUiStates: Map<WeekDay, CalendarListUiState>,
        ) : CalendarUiState

        data class GridTimetable(
            val timetableGridUiState: Map<WeekDay, CalendarGridUiState>,
        ) : CalendarUiState
    }

    data class CalendarListUiState(
        val taskList: List<TaskItem> = listOf()
    )

    data class CalendarGridUiState(
        val taskList: List<TaskItem> = listOf()
    )

    private val _calendarUiState = MutableStateFlow(CalendarUiState.Empty)
    val uiState = _calendarUiState.asStateFlow()
}