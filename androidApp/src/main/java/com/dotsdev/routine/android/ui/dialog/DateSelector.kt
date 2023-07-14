package com.dotsdev.routine.android.ui.dialog


import androidx.compose.material3.DatePicker
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeSelector(
    taskDueDate: Long?,
    onDateTimeSelected: (Long?) -> Unit,
    onClearDateTimeClick: () -> Unit
) {

    val dateRangePickerState = rememberDateRangePickerState()
    DateRangePicker(state = dateRangePickerState)

// Date picker with initial input display mode
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker
    )
    DatePicker(state = datePickerState)
}