package com.dotsdev.routine.android.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.util.backgroundColor
import com.dotsdev.routine.theme.AppTheme
import com.dotsdev.routine.theme.DarkColorScheme
import com.dotsdev.routine.theme.LightColorScheme
import com.dotsdev.routine.theme.RoutineDarkColors
import com.dotsdev.routine.theme.RoutineLightColors
import com.dotsdev.routine.theme.Type.typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun RoutineAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (darkTheme) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    } else {
        if (darkTheme) DarkColorScheme else LightColorScheme
    }
    val appThemeColors = if (darkTheme) RoutineDarkColors else RoutineLightColors

    val shapes = Shapes(
        small = RoundedCornerShape(24.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(12.dp)
    )
    val navigationBarColor = colorScheme.backgroundColor()
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = colorScheme.background,
            darkIcons = !darkTheme
        )
        systemUiController.setNavigationBarColor(
            color = navigationBarColor,
            darkIcons = !darkTheme
        )
    }

    AppTheme(
        appColors = appThemeColors,
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}