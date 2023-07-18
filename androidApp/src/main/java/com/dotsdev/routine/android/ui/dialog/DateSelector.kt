package com.dotsdev.routine.android.ui.dialog


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.ui.components.RoundedBorderItem
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource
import com.dotsdev.routine.util.toDueDayFormatted

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeSelector(
    taskDueDate: Long?,
    onDateTimeSelected: (Long?) -> Unit,
    onClearDateTimeClick: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = taskDueDate
    )
    val showDialog = rememberSaveable { mutableStateOf(false) }
    Column {
        Text(
            text = stringResource(MR.strings.date_time),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Row(
            modifier = Modifier
                .height(48.dp)
                .padding(top = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = taskDueDate != null) {
                OutlinedButton(
                    onClick = { showDialog.value = true },
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Icon(
                        Icons.Filled.CalendarMonth,
                        stringResource(MR.strings.clear),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = taskDueDate?.toDueDayFormatted().orEmpty(),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    IconButton(onClick = onClearDateTimeClick) {
                        Icon(
                            Icons.Filled.Close,
                            stringResource(MR.strings.clear),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            if (taskDueDate == null) {
                OutlinedButton(
                    onClick = { showDialog.value = true },
                    modifier = Modifier.padding(horizontal = 24.dp),
                ) {
                    Text(
                        text = stringResource(MR.strings.enter_date_time),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
    if (showDialog.value) {
        DatePickerDialog(
            onDismissRequest = { showDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog.value = false
                        onDateTimeSelected(datePickerState.selectedDateMillis)
                    }
                ) {
                    Text(stringResource(MR.strings.ok))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}