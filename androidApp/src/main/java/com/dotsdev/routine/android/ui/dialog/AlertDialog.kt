package com.dotsdev.routine.android.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource
import dev.icerock.moko.resources.StringResource

@Composable
fun Alert(
    icon: ImageVector? = null,
    title: StringResource? = null,
    message: StringResource? = null,
    onDismissRequest: () -> Unit = {},
    onPositiveClick: () -> Unit = {}
) {
    AppAlertDialog(
        icon = {
            icon?.let { Icon(it, contentDescription = null) }
        },
        title = {
            title?.let { Text(stringResource(title)) }
        },
        onDismissRequest = onDismissRequest,
        text = {
            message?.let { Text(stringResource(message)) }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onPositiveClick()
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
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    dismissButton: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit)? = null,
    text: @Composable (() -> Unit)? = null
) {
    PlatformAlertDialog(
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
fun PlatformAlertDialog(
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