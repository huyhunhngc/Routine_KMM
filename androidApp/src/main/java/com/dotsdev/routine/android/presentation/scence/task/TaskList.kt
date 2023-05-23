package com.dotsdev.routine.android.presentation.scence.task

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.ui.components.EmptyView
import com.dotsdev.routine.model.TaskItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TasksList(
    tasks: List<TaskItem>,
    onDoingClick: (String) -> Unit,
    onDoneClick: (String) -> Unit,
    onTaskItemClick: (String) -> Unit,
    onTaskItemLongClick: (String) -> Unit,
    onSwipeToDismiss: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val tasksDoing = tasks.filter { it.state == TaskItem.TaskState.DOING }
    val tasksDone = tasks.filter { it.state == TaskItem.TaskState.DONE }
    var areTasksDoneVisible by remember { mutableStateOf(false) }
    LazyColumn(modifier = modifier) {
        items(tasksDoing, key = { it.id }) { task ->
            SwipeableTaskItem(
                task,
                onDoingClick,
                onDoneClick,
                onTaskItemClick,
                onTaskItemLongClick,
                modifier = Modifier.animateItemPlacement()
            ) { onSwipeToDismiss(task.id) }
        }
        if (tasksDone.isNotEmpty()) {
            item {
                ListItem(
                    headlineText = {
                        Text(
                            text = stringResource(MR.strings.completed_tasks)
                        )
                    },
                    trailingContent = {
                        if (areTasksDoneVisible) {
                            Icon(
                                Icons.Filled.ExpandLess,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                Icons.Filled.ExpandMore,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.animateItemPlacement()
                        .clickable { areTasksDoneVisible = !areTasksDoneVisible }
                )
            }
        }
        if (areTasksDoneVisible) {
            items(tasksDone, key = { it.id }) { task ->
                SwipeableTaskItem(
                    task,
                    onDoingClick,
                    onDoneClick,
                    onTaskItemClick,
                    onTaskItemLongClick,
                    modifier = Modifier.animateItemPlacement()
                ) { onSwipeToDismiss(task.id) }
            }
        }
        item {
            Spacer(modifier = Modifier.height(84.dp))
        }
    }
    if (tasksDoing.isEmpty() && !areTasksDoneVisible) {
        EmptyView(
            painterResource(ToDometerIllustrations.CompletedTasks),
            stringResource(MR.strings.you_have_completed_all_tasks),
            stringResource(MR.strings.congratulations)
        )
    }
}