package com.dotsdev.routine.android.presentation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    navControllerBottomBar: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    AppState(navController, navControllerBottomBar, context)
}

class AppState(
    val navController: NavHostController,
    val navControllerBottomBar: NavHostController,
    private val context: Context
) {
    fun navigateBack() {
        navController.popBackStack()
    }
}