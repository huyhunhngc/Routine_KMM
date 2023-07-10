package com.dotsdev.routine.android.presentation.scence.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute.addTaskRoute
import com.dotsdev.routine.android.presentation.AppRoute.calendarRoute
import com.dotsdev.routine.android.presentation.AppRoute.podcastRoute
import com.dotsdev.routine.android.presentation.AppRoute.settings
import com.dotsdev.routine.android.presentation.AppRoute.taskRoute
import com.dotsdev.routine.android.presentation.scence.calendar.CalendarScreen
import com.dotsdev.routine.android.presentation.scence.task.TaskScreen
import com.dotsdev.routine.android.util.backgroundColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreen(
    darkTheme: Boolean = isSystemInDarkTheme(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit,
    navController: NavController,
    navControllerMainTab: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                currentOnStop()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                navController = navControllerMainTab,
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
            )
        }
    ) { padding ->
        NavHost(
            navController = navControllerMainTab,
            startDestination = taskRoute,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            mainTabGraph(
                navController = navController
            )
        }
    }
}

fun NavGraphBuilder.mainTabGraph(
    navController: NavController
) {
    composable(taskRoute) {
        TaskScreen(
            navigateToAddTaskList = {

            },
            navigateToEditTaskList = {

            },
            navigateToAddTask = {
                navController.navigate(addTaskRoute)
            },
            navigateToSettings = {
                navController.navigate(settings)
            },
            onTaskItemClick = {

            },
        )
    }
    composable(calendarRoute) {
        CalendarScreen()
    }
    composable(podcastRoute) {

    }
}