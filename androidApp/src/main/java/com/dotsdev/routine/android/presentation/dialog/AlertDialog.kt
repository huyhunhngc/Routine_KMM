package com.dotsdev.routine.android.presentation.dialog

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource

@Composable
fun DeleteTaskListAlertDialog(onDismissRequest: () -> Unit, onDeleteTaskListClick: () -> Unit) {
    ToDometerAlertDialog(
        icon = {
            Icon(Icons.Filled.Warning, contentDescription = null)
        },
        title = {
            Text(stringResource(MR.strings.delete_task_list))
        },
        onDismissRequest = onDismissRequest,
        text = {
            Text(stringResource(MR.strings.delete_task_list_question))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteTaskListClick()
                    onDismissRequest()
                }
            ) {
                Text(stringResource(MR.strings.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(MR.strings.cancel))
            }
        }
    )
}


@Composable
fun DeleteTaskAlertDialog(onDismissRequest: () -> Unit, onDeleteTaskClick: () -> Unit) {
    ToDometerAlertDialog(
        title = {
            Text(stringResource(MR.strings.delete_task))
        },
        onDismissRequest = onDismissRequest,
        text = {
            Text(stringResource(MR.strings.delete_task_question))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDeleteTaskClick()
                    onDismissRequest()
                }
            ) {
                Text(stringResource(MR.strings.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(stringResource(MR.strings.cancel))
            }
        }
    )
}

@Composable
fun ToDometerAlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null
) {
    //  Workaround to have default parameters in Kotlin Multiplatform.
    ToDometerPlatformAlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        modifier = modifier,
        dismissButton = dismissButton,
        icon = icon,
        title = title,
        text = text
    )
}

@Composable
fun ToDometerPlatformAlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier,
    dismissButton: @Composable (() -> Unit)?,
    icon: @Composable (() -> Unit)?,
    title: @Composable (() -> Unit)?,
    text: @Composable (() -> Unit)?
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        modifier = modifier,
        dismissButton = dismissButton,
        icon = icon,
        title = title,
        text = text
    )
}