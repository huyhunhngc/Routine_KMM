package com.dotsdev.routine.android.presentation.scence.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.model.TaskList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(): ViewModel() {
    data class TaskUiState(
        val isLoadingTasks: Boolean = false,
        val tasks: List<TaskItem> = emptyList(),
        val taskLists: List<TaskList> = emptyList(),
        val taskListSelected: TaskList? = null,
        val isDefaultTaskListSelected: Boolean = true,
    )

    private val _taskUiState = MutableStateFlow(TaskUiState(isLoadingTasks = true))
    val homeUiState = _taskUiState.asStateFlow()

    init {
        _taskUiState.update {
            it.copy(isLoadingTasks = false)
        }
    }

    fun deleteTask(id: String) = viewModelScope.launch {
        //deleteTaskUseCase(id)
    }

    fun deleteTaskList() = viewModelScope.launch {
        //deleteTaskListSelectedUseCase()
    }

    fun setTaskDoing(id: String) = viewModelScope.launch {
        //setTaskDoingUseCase(id)
    }

    fun setTaskDone(id: String) = viewModelScope.launch {
        //setTaskDoneUseCase(id)
    }

    fun setTaskListSelected(id: String) = viewModelScope.launch {
        //setTaskListSelectedUseCase(id)
    }
}