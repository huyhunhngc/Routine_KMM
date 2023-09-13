package com.dotsdev.routine.android.presentation.scence.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.navigation.compose.rememberNavController
import com.dotsdev.routine.android.presentation.AppRoute
import com.dotsdev.routine.android.presentation.AppRoute.taskRoute

fun NavGraphBuilder.mainTabScreens(
    onStartMainFlow: () -> Unit,
    onStopMainFlow: () -> Unit,
    mainNavGraph: NavGraphBuilder.(NavController, PaddingValues) -> Unit,
) {
    composable(AppRoute.mainTabRoute) {
        MainScreen(
            onStart = onStartMainFlow,
            onStop = onStopMainFlow,
            mainNavGraph = mainNavGraph,
        )
    }
}

@Composable
fun MainScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit,
    mainNavGraph: NavGraphBuilder.(NavController, PaddingValues) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val mainTabNavController = rememberNavController()
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
        bottomBar = {
            BottomNavigationBar(
                navController = mainTabNavController,
                modifier = Modifier.fillMaxWidth().zIndex(1f)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) { padding ->
        NavHost(
            navController = mainTabNavController,
            startDestination = taskRoute,
            modifier = Modifier.fillMaxSize().padding(padding),
            enterTransition = { materialFadeThroughIn() },
            exitTransition = { materialFadeThroughOut() },
        ) {
            mainNavGraph(mainTabNavController, padding)
        }
    }
}

private fun materialFadeThroughIn(): EnterTransition = fadeIn(
    animationSpec = tween(
        durationMillis = 195,
        delayMillis = 105,
        easing = LinearOutSlowInEasing,
    ),
) + scaleIn(
    animationSpec = tween(
        durationMillis = 195,
        delayMillis = 105,
        easing = LinearOutSlowInEasing,
    ),
    initialScale = 0.92f,
)

private fun materialFadeThroughOut(): ExitTransition = fadeOut(
    animationSpec = tween(
        durationMillis = 105,
        delayMillis = 0,
        easing = FastOutLinearInEasing,
    ),
)