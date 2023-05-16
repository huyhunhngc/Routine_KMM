package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dotsdev.routine.model.TaskItem

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
                Text(
                    text = "Routine",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                )
            },
            actions = {
                IconButton(onClick = onMoreClick) {
                    Icon(
                        painterResource(ToDometerIcons.MoreVert),
                        contentDescription = ""
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