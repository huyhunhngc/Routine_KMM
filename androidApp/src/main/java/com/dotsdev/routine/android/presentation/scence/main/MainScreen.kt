package com.dotsdev.routine.android.presentation.scence.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute.calendarRoute
import com.dotsdev.routine.android.presentation.AppRoute.podcastRoute
import com.dotsdev.routine.android.presentation.AppRoute.taskRoute
import com.dotsdev.routine.android.presentation.scence.task.TaskScreen
import kotlin.math.max

@Composable
fun MainScreen(
    navController: NavController,
    navControllerBottomBar: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navControllerBottomBar,
            startDestination = taskRoute,
            modifier = Modifier.fillMaxSize(),
        ) {
            mainTabGraph(
                navController = navControllerBottomBar
            )
        }
        BottomNavigationBar(
            navController = navControllerBottomBar,
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1f)
        )
    }
}

fun NavGraphBuilder.mainTabGraph(
    navController: NavController
) {
    composable(taskRoute) {
        TaskScreen()
    }
    composable(calendarRoute) {

    }
    composable(podcastRoute) {

    }
}