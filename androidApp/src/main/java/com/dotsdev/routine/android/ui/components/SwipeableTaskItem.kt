package com.dotsdev.routine.android.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.util.Alpha.alphaMedium
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.resources.stringResource
import com.dotsdev.routine.theme.AppTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
internal fun SwipeableTaskItem(
    taskItem: TaskItem,
    onDoingClick: (String) -> Unit,
    onDoneClick: (String) -> Unit,
    onTaskItemClick: (String) -> Unit,
    onTaskItemLongClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    onSwipeToDismiss: () -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToEnd) {
                onSwipeToDismiss()
            }
            it != DismissValue.DismissedToEnd
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (dismissState.targetValue == DismissValue.Default) {
            MaterialTheme.colorScheme.outline
        } else {
            MaterialTheme.colorScheme.error
        },
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    )
    val backgroundIconTint by animateColorAsState(
        targetValue = if (dismissState.targetValue == DismissValue.Default) {
            MaterialTheme.colorScheme.onSurface.alphaMedium
        } else {
            MaterialTheme.colorScheme.onError
        },
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    )
    val taskItemCornerRadius by animateDpAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.dp else 8.dp,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        )
    )
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd),
        dismissThresholds = {
            FractionalThreshold(0.1f)
        },
        background = {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                TaskItemBackgroundIcon(dismissState, backgroundIconTint)
            }
        },
        dismissContent = {
            Surface(
                shape = RoundedCornerShape(taskItemCornerRadius),
                modifier = modifier
                    .combinedClickable(
                        onClick = {
                            onTaskItemClick(taskItem.id)
                        },
                        onLongClick = {
                            onTaskItemLongClick(taskItem.id)
                        }
                    )
                    .fillMaxWidth()
            ) {
                Column {
                    TaskItemHeadlineContent(
                        taskItem = taskItem,
                        onDoingClick = onDoingClick,
                        onDoneClick = onDoneClick
                    )
                    TaskItemSupportingContent(taskItem)
                }
            }
        },
        modifier = modifier
    )
}

@Composable
private fun TaskItemHeadlineContent(
    taskItem: TaskItem,
    onDoingClick: (String) -> Unit,
    onDoneClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            start = 12.dp,
            end = 16.dp
        )
    ) {
        if (taskItem.tag != TaskItem.Tag.UNSPECIFIED) {
            TaskTagIndicator(taskItem.tag)
        }
        Text(
            taskItem.title,
            textDecoration = taskItemTitleTextDecoration(taskItem.state),
            color = taskItemTitleColor(taskItem.state),
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(
            onClick = {
                if (taskItem.state == TaskItem.TaskState.DONE) {
                    onDoingClick(taskItem.id)
                } else {
                    onDoneClick(taskItem.id)
                }
            }
        ) {
            Icon(
                taskItemActionIcon(taskItem.state),
                contentDescription = taskItemActionContentDescription(taskItem.state),
                tint = AppTheme.appColors.check
            )
        }
    }
}

@Composable
private fun TaskItemSupportingContent(taskItem: TaskItem) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.padding(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (taskItem.state == TaskItem.TaskState.DOING) {
            taskItem.dueDate?.let { dueDate ->
                item {
                    TaskDueDateChip(dueDate, modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }
        if (taskItem.totalChecklistItems > 0) {
            item {
                TaskChecklistItemsChip(taskItem.checklistItemsDone, taskItem.totalChecklistItems)
            }
        }
    }
}

@Composable
private fun taskItemTitleColor(state: TaskItem.TaskState): Color =
    when (state) {
        TaskItem.TaskState.DOING -> MaterialTheme.colorScheme.onSurface
        TaskItem.TaskState.DONE -> MaterialTheme.colorScheme.onSurface.alphaMedium
    }

private fun taskItemTitleTextDecoration(state: TaskItem.TaskState): TextDecoration =
    when (state) {
        TaskItem.TaskState.DOING -> TextDecoration.None
        TaskItem.TaskState.DONE -> TextDecoration.LineThrough
    }

@Composable
private fun taskItemActionIcon(state: TaskItem.TaskState) =
    when (state) {
        TaskItem.TaskState.DOING -> Icons.Filled.Check
        TaskItem.TaskState.DONE -> Icons.Filled.Replay
    }

@Composable
private fun taskItemActionContentDescription(state: TaskItem.TaskState): String =
    when (state) {
        TaskItem.TaskState.DOING -> stringResource(MR.strings.check_task)
        TaskItem.TaskState.DONE -> stringResource(MR.strings.uncheck_task)
    }
