package com.dotsdev.routine.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dotsdev.routine.theme.AppColors

data class TaskItem(
    val id: String,
    val title: String,
    val state: TaskState,
    val taskListId: String,
    val tag: Tag,
    val sync: Boolean,
    val dueDate: Long?,
    val taskChecklistItem: List<TaskChecklistItem>,
    val checklistItemsDone: Long,
    val totalChecklistItems: Long
) {
    data class TaskChecklistItem(
        val id: String,
        val text: String,
        val state: TaskChecklistItemState,
        val taskId: String
    ) {
        enum class TaskChecklistItemState {
            UNCHECKED,
            CHECKED
        }
    }
    enum class TaskState {
        DOING,
        DONE;

        override fun toString(): String {
            return name
        }
    }

    enum class Tag {
        UNSPECIFIED,
        PINK,
        RED,
        INDIGO,
        BLUE,
        TEAL,
        GREEN,
        LIME,
        YELLOW,
        AMBER,
        ORANGE,
        BROWN,
        GRAY;

        companion object {
            @Composable
            fun AppColors.composeColorOf(tag: Tag): Color =
                when (tag) {
                    UNSPECIFIED -> Color.Unspecified
                    PINK -> pink
                    RED -> red
                    INDIGO -> indigo
                    BLUE -> blue
                    TEAL -> teal
                    GREEN -> green
                    LIME -> lime
                    YELLOW -> yellow
                    AMBER -> amber
                    ORANGE -> orange
                    BROWN -> brown
                    GRAY -> gray
                }
        }
    }
}

