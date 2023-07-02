package com.dotsdev.routine.android.presentation.scence.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotsdev.routine.android.ui.components.CalendarEventItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(viewModel: CalendarViewModel = hiltViewModel()) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            CalendarEventItem()
        }
    }
}