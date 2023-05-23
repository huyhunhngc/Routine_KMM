package com.dotsdev.routine.model

data class TaskList(
    val id: String,
    val name: String,
    val description: String,
    val sync: Boolean
) {
    override fun toString() = name
}