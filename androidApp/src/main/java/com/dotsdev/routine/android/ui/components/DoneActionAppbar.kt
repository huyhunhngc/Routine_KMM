package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DoneActionAppBar(
    navigateBack: () -> Unit,
    title: String,
    isSaveButtonEnabled: Boolean,
    onSaveButtonClick: () -> Unit,
    saveButtonTintColor: Color = MaterialTheme.colorScheme.primary
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = stringResource(MR.strings.back)
                )
            }
        },
        title = { Text(title) },
        actions = {
            TextButton(
                enabled = isSaveButtonEnabled,
                onClick = onSaveButtonClick
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = stringResource(MR.strings.save),
                    tint = saveButtonTintColor
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(MR.strings.save),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alignByBaseline(),
                    color = saveButtonTintColor
                )
            }
        }
    )
}