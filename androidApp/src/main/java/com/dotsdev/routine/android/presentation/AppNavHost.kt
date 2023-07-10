package com.dotsdev.routine.android.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.dotsdev.routine.android.presentation.AppRoute.addTaskRoute
import com.dotsdev.routine.android.presentation.AppRoute.mainTabRoute
import com.dotsdev.routine.android.presentation.AppRoute.settings
import com.dotsdev.routine.android.presentation.scence.main.MainScreen
import com.dotsdev.routine.android.presentation.scence.settings.SettingsScreen
import com.dotsdev.routine.android.presentation.scence.task.addtask.AddTaskScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    navControllerBottomBar: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    onStartMainFlow: () -> Unit,
    onStopMainFlow: () -> Unit,
    startDestination: String = mainTabRoute,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(mainTabRoute) {
            MainScreen(
                darkTheme = darkTheme,
                onStart = onStartMainFlow,
                onStop = onStopMainFlow,
                navController = navController,
                navControllerMainTab = navControllerBottomBar
            )
        }
        composableAnimated(addTaskRoute) {
            AddTaskScreen()
        }
        composableAnimated(settings) {
            SettingsScreen(navController)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableAnimated(
    route: String,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)) }
    ) {
        content(it)
    }
}
