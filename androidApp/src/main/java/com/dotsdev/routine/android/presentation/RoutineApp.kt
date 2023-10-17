package com.dotsdev.routine.android.presentation

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dotsdev.routine.util.backgroundColor
import com.dotsdev.routine.model.preferences.AppThemeType
import com.dotsdev.routine.theme.AppTheme
import com.dotsdev.routine.theme.DarkColorScheme
import com.dotsdev.routine.theme.LightColorScheme
import com.dotsdev.routine.theme.RoutineDarkColors
import com.dotsdev.routine.theme.RoutineLightColors
import com.dotsdev.routine.theme.Type.typography
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun RoutineApp(
    viewModel: RoutineMainViewModel = hiltViewModel()
) {
    val appThemeState = viewModel.appTheme.collectAsState()
    val darkTheme: Boolean = when (appThemeState.value) {
        AppThemeType.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        AppThemeType.DARK_THEME -> true
        AppThemeType.LIGHT_THEME -> false
    }

    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkColorScheme else LightColorScheme
    }
    val appThemeColors = if (darkTheme) RoutineDarkColors else RoutineLightColors

    val shapes = Shapes(
        small = RoundedCornerShape(24.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(12.dp)
    )
    val keyboardController = LocalSoftwareKeyboardController.current
    val navigationBarColor = colorScheme.backgroundColor()
    val systemUiController = rememberSystemUiController()
    val coroutineScope = rememberCoroutineScope()
    val navController: NavHostController = rememberNavController()
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
        shapes = shapes
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorScheme.background
        ) {
            AppNavHost(
                navController = navController,
                onBackClick = {
                    keyboardController?.hide()
                    navController.popBackStack()
                },
                onBackClickBlockNavController = {
                    keyboardController?.hide()
                    popBackStack()
                },
                modifier = Modifier,
                startDestination = AppRoute.mainTabRoute,
                onStartMainFlow = {
                    coroutineScope.launch {
                        systemUiController.setNavigationBarColor(
                            color = navigationBarColor,
                            darkIcons = !darkTheme
                        )
                    }
                },
                onStopMainFlow = {
                    coroutineScope.launch {
                        systemUiController.setNavigationBarColor(
                            color = colorScheme.background,
                            darkIcons = !darkTheme
                        )
                    }
                }
            )
        }
    }
}