package com.dotsdev.routine.android.presentation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberAppState(
    navController: NavHostController = rememberAnimatedNavController(),
    navControllerBottomBar: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(context) {
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