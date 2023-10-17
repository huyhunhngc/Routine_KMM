package com.dotsdev.routine.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dotsdev.routine.model.TaskItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    onMenuClick: () -> Unit,
    onMoreClick: () -> Unit,
    onSettingClick: () -> Unit,
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
                        Icons.Filled.MoreVert,
                        contentDescription = null
                    )
                }
                IconButton(onClick = onSettingClick) {
                    Icon(
                        Icons.Filled.Settings,
                        contentDescription = null
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
        TaskProgress(taskListName, tasks)
    }
}

@Composable
internal fun HomeMoreDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onEditTaskListClick: () -> Unit,
    onDeleteTaskListClick: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        DropdownMenuItem(
            onClick = onEditTaskListClick,
            leadingIcon = {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = null
                )
            },
            text = {
                Text(
                    "Edit",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
        DropdownMenuItem(
            onClick = onDeleteTaskListClick,
            leadingIcon = {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = null
                )
            },
            text = {
                Text(
                    "Delete",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        )
    }
}
