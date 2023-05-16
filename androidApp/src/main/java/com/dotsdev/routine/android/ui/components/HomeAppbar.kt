package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAppBar(
    onMenuClick: () -> Unit,
    onMoreClick: () -> Unit,
    onHomeMoreDropdownDismissRequest: () -> Unit,
    homeMoreDropdownExpanded: Boolean,
    onEditTaskListClick: () -> Unit,
    onDeleteTaskListClick: () -> Unit,
    taskListName: String?,
    tasks: List<TaskItem>
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                ToDometerTitle()
            },
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(
                        painterResource(ToDometerIcons.Menu),
                        contentDescription = stringResource(MR.strings.menu)
                    )
                }
            },
            actions = {
                IconButton(onClick = onMoreClick) {
                    Icon(
                        painterResource(ToDometerIcons.MoreVert),
                        contentDescription = stringResource(MR.strings.more)
                    )
                }
                HomeMoreDropdownMenu(
                    onEditTaskListClick = onEditTaskListClick,
                    onDeleteTaskListClick = onDeleteTaskListClick,
                    expanded = homeMoreDropdownExpanded,
                    onDismissRequest = onHomeMoreDropdownDismissRequest
                )
            }
        )
        TaskListProgress(taskListName, tasks)
        ToDometerDivider()
    }
}