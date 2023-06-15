package com.dotsdev.routine.android.presentation.scence.task.addtask

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotsdev.routine.android.ui.components.DoneActionAppBar
import com.dotsdev.routine.android.util.Alpha.alphaMedium
import com.dotsdev.routine.resources.stringResource
import com.dotsdev.routine.resources.MR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val addTaskUiState by viewModel.addTaskUiState.collectAsState()
    val lazyListState = rememberLazyListState()
    val snackbarHostState = remember { SnackbarHostState() }
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            DoneActionAppBar(
                navigateBack = {},
                title = stringResource(MR.strings.add_task),
                isSaveButtonEnabled = true,
                onSaveButtonClick = {

                },
                saveButtonTintColor = if (addTaskUiState.isAddingTask) {
                    MaterialTheme.colorScheme.onSurface.alphaMedium
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )
        },
        content = { padding ->
            if (addTaskUiState.isAddingTask) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth().padding(padding))
            }
        }
    )
}