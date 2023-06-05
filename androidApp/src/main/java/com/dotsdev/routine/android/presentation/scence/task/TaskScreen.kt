package com.dotsdev.routine.android.presentation.scence.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotsdev.routine.android.R
import com.dotsdev.routine.android.presentation.dialog.DeleteTaskAlertDialog
import com.dotsdev.routine.android.presentation.dialog.DeleteTaskListAlertDialog
import com.dotsdev.routine.android.ui.components.AppLoading
import com.dotsdev.routine.android.ui.components.EmptyView
import com.dotsdev.routine.android.ui.components.HomeAppBar
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun TaskScreen(
    navigateToAddTaskList: () -> Unit,
    navigateToEditTaskList: () -> Unit,
    navigateToAddTask: () -> Unit,
    onTaskItemClick: (String) -> Unit,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.homeUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val snackbarHostState = remember { SnackbarHostState() }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val closeDrawer: suspend () -> Unit = {
        drawerState.close()
    }

    var selectedTask by remember { mutableStateOf("") }
    var deleteTaskAlertDialogState by remember { mutableStateOf(false) }
    var deleteTaskListAlertDialogState by remember { mutableStateOf(false) }

    val defaultTaskListName = stringResource(MR.strings.default_task_list_name)

    var homeMoreDropdownExpanded by remember { mutableStateOf(false) }

    val cannotEditTaskList = stringResource(MR.strings.cannot_edit_this_task_list)
    val cannotDeleteTaskList = stringResource(MR.strings.cannot_delete_this_task_list)
    val snackbarActionLabel = stringResource(MR.strings.ok)

    Scaffold(
        topBar = {
            HomeAppBar(
                onMenuClick = { scope.launch { drawerState.open() } },
                onMoreClick = { homeMoreDropdownExpanded = true },
                onHomeMoreDropdownDismissRequest = { homeMoreDropdownExpanded = false },
                homeMoreDropdownExpanded = homeMoreDropdownExpanded,
                onEditTaskListClick = {
                    if (homeUiState.isDefaultTaskListSelected) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                cannotEditTaskList,
                                snackbarActionLabel
                            )
                        }
                    } else {
                        navigateToEditTaskList()
                    }
                    homeMoreDropdownExpanded = false
                },
                onDeleteTaskListClick = {
                    if (homeUiState.isDefaultTaskListSelected) {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                cannotDeleteTaskList,
                                snackbarActionLabel
                            )
                        }
                    } else {
                        deleteTaskListAlertDialogState = true
                    }
                    homeMoreDropdownExpanded = false
                },
                taskListName = homeUiState.taskListSelected?.name ?: defaultTaskListName,
                tasks = homeUiState.tasks
            )
        },
        content = { paddingValues ->
            if (deleteTaskAlertDialogState) {
                DeleteTaskAlertDialog(
                    onDismissRequest = { deleteTaskAlertDialogState = false },
                    onDeleteTaskClick = {
                        viewModel.deleteTask(selectedTask)
                    }
                )
            }
            if (deleteTaskListAlertDialogState) {
                DeleteTaskListAlertDialog(
                    onDismissRequest = { deleteTaskListAlertDialogState = false },
                    onDeleteTaskListClick = {
                        viewModel.deleteTaskList()
                        scope.launch { sheetState.hide() }
                    }
                )
            }
            if (homeUiState.isLoadingTasks) {
                AppLoading()
            } else {
                if (homeUiState.tasks.isEmpty()) {
                    EmptyView(
                        painterResource(R.drawable.ic_no_tasks),
                        stringResource(MR.strings.no_tasks)
                    )
                } else {
                    TasksList(
                        homeUiState.tasks,
                        onDoingClick = {
                            viewModel.setTaskDoing(it)
                        },
                        onDoneClick = {
                            viewModel.setTaskDone(it)
                        },
                        onTaskItemClick = onTaskItemClick,
                        onTaskItemLongClick = {
                            deleteTaskAlertDialogState = true
                            selectedTask = it
                        },
                        onSwipeToDismiss = {
                            deleteTaskAlertDialogState = true
                            selectedTask = it
                        },
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddTask
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = null
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    )
}