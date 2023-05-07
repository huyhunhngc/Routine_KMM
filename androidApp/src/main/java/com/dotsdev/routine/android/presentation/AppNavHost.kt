package com.dotsdev.routine.android.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.dotsdev.routine.android.presentation.AppRoute.mainRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    navControllerBottomBar: NavHostController,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = mainRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

    }
}
