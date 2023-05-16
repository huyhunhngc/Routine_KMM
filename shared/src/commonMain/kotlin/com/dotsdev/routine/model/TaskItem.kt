package com.dotsdev.routine.model

data class TaskItem(
    val id: String,
    val title: String,
    val state: TaskState,
    val taskListId: String,
    val tag: Tag,
    val sync: Boolean,
    val dueDate: Long?,
    val checklistItemsDone: Long,
    val totalChecklistItems: Long
) {
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
        GRAY
    }

}

