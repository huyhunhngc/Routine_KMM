package com.dotsdev.routine.android.presentation.scence.task.addtask

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor() : ViewModel() {
    data class AddTaskUiState(
        val isAddingTask: Boolean = false,
        val isAdded: Boolean = false,
        val isError: Boolean = false
    )

    private val _addTaskUiState = MutableStateFlow(AddTaskUiState())
    val addTaskUiState = _addTaskUiState.asStateFlow()
}