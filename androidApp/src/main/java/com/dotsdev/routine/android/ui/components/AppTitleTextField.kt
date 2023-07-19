package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.util.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTitledTextField(
    modifier: Modifier = Modifier,
    title: String = "",
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = false,
    maxLines: Int = 10,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.backgroundColor(),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = MaterialTheme.colorScheme.error
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(
        modifier = modifier
    ) {
        Text(
            title,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(start = 16.dp)
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            placeholder = placeholder,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .fillMaxWidth(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError,
            singleLine = singleLine,
            maxLines = maxLines,
            colors = colors
        )
        if (isError) {
            Text(
                errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}