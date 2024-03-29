package com.dotsdev.routine.android.presentation.scence.task

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.DrawerValue
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.R
import com.dotsdev.routine.android.presentation.AppRoute
import com.dotsdev.routine.ui.components.AppLoading
import com.dotsdev.routine.ui.components.EmptyView
import com.dotsdev.routine.ui.components.HomeAppBar
import com.dotsdev.routine.android.ui.dialog.Alert
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource
import kotlinx.coroutines.launch

fun NavGraphBuilder.taskScreens(
    navigateToAddTaskList: () -> Unit,
    navigateToEditTaskList: () -> Unit,
    navigateToAddTask: () -> Unit,
    navigateToSettings: () -> Unit,
    onTaskItemClick: (String) -> Unit,
) {
    composable(AppRoute.taskRoute) {
        TaskScreen(
            navigateToAddTaskList = navigateToAddTaskList,
            navigateToEditTaskList = navigateToEditTaskList,
            navigateToAddTask = navigateToAddTask,
            navigateToSettings = navigateToSettings,
            onTaskItemClick = onTaskItemClick,
        )
    }
}

@OptIn(
    ExperimentalMaterialApi::class,
)
@Composable
fun TaskScreen(
    navigateToAddTaskList: () -> Unit,
    navigateToEditTaskList: () -> Unit,
    navigateToAddTask: () -> Unit,
    navigateToSettings: () -> Unit,
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

    var selectedTask by remember { mutableStateOf("") }
    var deleteTaskAlertDialogState by remember { mutableStateOf(false) }
    var deleteTaskListAlertDialogState by remember { mutableStateOf(false) }
    var homeMoreDropdownExpanded by remember { mutableStateOf(false) }

    val defaultTaskListName = stringResource(MR.strings.default_task_list_name)
    val cannotEditTaskList = stringResource(MR.strings.cannot_edit_this_task_list)
    val cannotDeleteTaskList = stringResource(MR.strings.cannot_delete_this_task_list)
    val snackbarActionLabel = stringResource(MR.strings.ok)

    Scaffold(
        topBar = {
            HomeAppBar(
                onMenuClick = { scope.launch { drawerState.open() } },
                onMoreClick = { homeMoreDropdownExpanded = true },
                onSettingClick = navigateToSettings,
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
                Alert(
                    title = MR.strings.delete_task,
                    message = MR.strings.delete_task_question,
                    icon = Icons.Filled.Warning,
                    onDismissRequest = { deleteTaskAlertDialogState = false },
                    onPositiveClick = {
                        viewModel.deleteTask(selectedTask)
                    }
                )
            }
            if (deleteTaskListAlertDialogState) {
                Alert(
                    title = MR.strings.delete_task_list,
                    message = MR.strings.delete_task_list_question,
                    icon = Icons.Filled.Warning,
                    onDismissRequest = { deleteTaskListAlertDialogState = false },
                    onPositiveClick = {
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