package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.FormatColorReset
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.model.TaskItem.Tag.Companion.composeColorOf
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource
import com.dotsdev.routine.theme.AppTheme

@Composable
internal fun TagSelector(selectedTag: TaskItem.Tag, onSelected: (TaskItem.Tag) -> Unit) {
    val tags = enumValues<TaskItem.Tag>()
    val state = rememberLazyListState()
    Text(
        text = stringResource(MR.strings.choose_tag),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.labelLarge,
        modifier = Modifier.padding(horizontal = 24.dp)
    )
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp),
        state = state
    ) {
        items(tags) { tag ->
            TagItem(
                tag = tag,
                selected = selectedTag == tag,
                onClick = {
                    onSelected(tag)
                }
            )
        }
    }
    LaunchedEffect(selectedTag) {
        state.animateScrollToItem(tags.indexOf(selectedTag))
    }
}

@Composable
private fun TagItem(
    tag: TaskItem.Tag,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = androidx.compose.ui.Modifier
            .size(42.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    ) {
        val color = AppTheme.appColors.composeColorOf(tag)
        val borderColor = if (color == Color.Unspecified) {
            MaterialTheme.colorScheme.outline
        } else {
            Color.Unspecified
        }
        val checkColor = if (color == Color.Unspecified) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.background
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .border(width = 1.dp, color = borderColor, shape = CircleShape)
                .clip(CircleShape)
                .background(color)

        ) {
            if (selected) {
                Icon(
                    Icons.Filled.Check,
                    stringResource(MR.strings.selected),
                    tint = checkColor
                )
            }
            if (tag == TaskItem.Tag.UNSPECIFIED && !selected) {
                Icon(
                    Icons.Rounded.FormatColorReset,
                    null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}