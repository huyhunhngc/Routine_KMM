package com.dotsdev.routine.android.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Alpha {
    const val High: Float = 1f
    const val Medium: Float = 0.6f
    const val Disabled: Float = 0.38f

    val Color.alphaMedium
        @Composable
        get() = withAlpha(Medium)
}

@Composable
fun Color.withAlpha(alpha: Float): Color = copy(alpha = alpha)