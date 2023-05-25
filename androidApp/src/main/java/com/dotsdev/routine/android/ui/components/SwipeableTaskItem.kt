package com.dotsdev.routine.android.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.util.Alpha.alphaMedium
import com.dotsdev.routine.model.TaskItem

@OptIn(ExperimentalMaterialApi::class)
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
                Modifier.fillMaxSize().background(backgroundColor).padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                TaskItemBackgroundIcon(dismissState, backgroundIconTint)
            }
        },
        dismissContent = {
            Surface(
                shape = RoundedCornerShape(taskItemCornerRadius),
                modifier = modifier.combinedClickable(
                    onClick = {
                        onTaskItemClick(taskItem.id)
                    },
                    onLongClick = {
                        onTaskItemLongClick(taskItem.id)
                    }
                ).fillMaxWidth()
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
