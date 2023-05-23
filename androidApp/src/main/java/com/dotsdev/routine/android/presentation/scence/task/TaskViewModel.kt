package com.dotsdev.routine.android.presentation.scence.task

import androidx.lifecycle.ViewModel
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.model.TaskList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(): ViewModel() {
    data class HomeUiState(
        val isLoadingTasks: Boolean = false,
        val tasks: List<TaskItem> = emptyList(),
        val taskLists: List<TaskList> = emptyList(),
        val taskListSelected: TaskList? = null,
        val isDefaultTaskListSelected: Boolean = true,
    )

    private val _homeUiState = MutableStateFlow(HomeUiState(isLoadingTasks = true))
    val homeUiState = _homeUiState.asStateFlow()
}