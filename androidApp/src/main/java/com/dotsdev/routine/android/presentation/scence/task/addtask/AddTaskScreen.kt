package com.dotsdev.routine.android.presentation.scence.task.addtask

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotsdev.routine.android.ui.components.AppTitledTextField
import com.dotsdev.routine.android.ui.components.DoneActionAppBar
import com.dotsdev.routine.android.ui.components.TagSelector
import com.dotsdev.routine.android.ui.dialog.DateTimeSelector
import com.dotsdev.routine.android.util.Alpha.alphaMedium
import com.dotsdev.routine.model.TaskItem
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    onBackClick: () -> Unit,
    viewModel: AddTaskViewModel = hiltViewModel()
) {
    val addTaskUiState by viewModel.addTaskUiState.collectAsState()
    val lazyListState = rememberLazyListState()
    val snackBarHostState = remember { SnackbarHostState() }
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topAppBarState)
    var discardTaskAlertDialogState by remember { mutableStateOf(false) }

    var taskTitle by rememberSaveable { mutableStateOf("") }
    var taskTitleInputError by remember { mutableStateOf(false) }
    var taskDescription by rememberSaveable { mutableStateOf("") }
    val tags = enumValues<TaskItem.Tag>()
    var selectedTag by rememberSaveable {
        mutableStateOf(
            tags.firstOrNull() ?: TaskItem.Tag.UNSPECIFIED
        )
    }
    var taskDueDate: Long? by rememberSaveable { mutableStateOf(null) }
    val taskChecklistItems: MutableList<String> by rememberSaveable {
        mutableStateOf(mutableListOf())
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            DoneActionAppBar(
                navigateBack = onBackClick,
                title = stringResource(MR.strings.add_task),
                isSaveButtonEnabled = true,
                onSaveButtonClick = {

                },
                saveButtonTintColor = if (addTaskUiState.isAddingTask) {
                    MaterialTheme.colorScheme.onSurface.alphaMedium
                } else {
                    MaterialTheme.colorScheme.primary
                }
            )
        },
        content = { padding ->
            if (addTaskUiState.isAddingTask) {
                LinearProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding))
            }
            LazyColumn(state = lazyListState, modifier = Modifier.padding(padding)) {
                item {
                    AppTitledTextField(
                        title = stringResource(MR.strings.name),
                        value = taskTitle,
                        onValueChange = {
                            taskTitle = it
                            taskTitleInputError = false
                        },
                        placeholder = { Text(stringResource(MR.strings.enter_task_name)) },
                        isError = taskTitleInputError,
                        errorMessage = stringResource(MR.strings.field_not_empty),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.padding(8.dp)
                    )
                }
                item {
                    AppTitledTextField(
                        title = stringResource(MR.strings.description),
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        placeholder = { Text(stringResource(MR.strings.enter_description)) },
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.padding(8.dp),
                        maxLines = 4
                    )
                }
                item {
                    TagSelector(selectedTag) { tag ->
                        selectedTag = tag
                    }
                }
                item {
                    DateTimeSelector(
                        taskDueDate,
                        onDateTimeSelected = { taskDueDate = it },
                        onClearDateTimeClick = { taskDueDate = null }
                    )
                }
                item {
                    Text(
                        text = stringResource(MR.strings.checklist),
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(
                            start = 24.dp,
                            top = 16.dp,
                            end = 24.dp
                        )
                    )
                }
                itemsIndexed(taskChecklistItems) { index, item ->
                    TaskChecklistItem(
                        item,
                        onDeleteTaskCheckListItem = { taskChecklistItems.removeAt(index) }
                    )
                }
                item {
//                    AddChecklistItemField(
//                        placeholder = { Text(stringResource(MR.strings.add_element_optional)) },
//                        onAddTaskCheckListItem = { taskChecklistItems.add(it) }
//                    )
                }
            }
//            if (discardTaskAlertDialogState) {
//                DiscardTaskAlertDialog(
//                    onDismissRequest = { discardTaskAlertDialogState = false },
//                    onConfirmButtonClick = navigateBack
//                )
//            }
        }
    )
}

@Composable
private fun TaskChecklistItem(
    taskChecklistItem: String,
    onDeleteTaskCheckListItem: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = taskChecklistItem,
            modifier = Modifier.weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = onDeleteTaskCheckListItem) {
            Icon(
                Icons.Filled.Close,
                contentDescription = stringResource(MR.strings.clear),
                tint = MaterialTheme.colorScheme.onSurface.alphaMedium
            )
        }
    }
}