package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AppLoading() {
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}