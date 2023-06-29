package com.dotsdev.routine.android.presentation.scence.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.dotsdev.routine.android.presentation.scence.task.TaskScreen

@Composable
fun MainScreen(
    navController: NavController,
    navControllerMainTab: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
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

    }
    composable(podcastRoute) {

    }
}