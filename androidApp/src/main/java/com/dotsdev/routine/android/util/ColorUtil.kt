package com.dotsdev.routine.android.util

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import com.dotsdev.routine.android.util.Alpha.disabled

object Alpha {
    const val High: Float = 1f
    private const val Medium: Float = 0.6f
    private const val Disabled: Float = 0.2f

    val Color.alphaMedium
        @Composable
        get() = withAlpha(Medium)
    val Color.disabled
        @Composable
        get() = withAlpha(Disabled)
}

@Composable
fun Color.withAlpha(alpha: Float): Color = copy(alpha = alpha)

@Composable
fun ColorScheme.backgroundColor(): Color = inversePrimary.disabled.compositeOver(background)