package com.dotsdev.routine.android.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute.mainRoute
import com.dotsdev.routine.android.presentation.AppRoute.mainTabRoute
import com.dotsdev.routine.android.presentation.scence.main.MainScreen
import com.dotsdev.routine.android.presentation.scence.main.mainTabGraph
import com.dotsdev.routine.android.presentation.scence.task.TaskScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    navControllerBottomBar: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = mainTabRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(mainTabRoute) {
            MainScreen(navController, navControllerBottomBar)
        }
    }
}
