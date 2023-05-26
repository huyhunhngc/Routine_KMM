package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.model.TaskItem.Tag.Companion.composeColorOf
import com.dotsdev.routine.theme.AppTheme

@Composable
fun TaskTagIndicator(
    tag: TaskItem.Tag,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = AppTheme.appColors.composeColorOf(tag))
            .height(20.dp)
            .width(4.dp)
    )
    Spacer(
        modifier.size(12.dp)
    )
}