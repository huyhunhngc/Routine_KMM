package com.dotsdev.routine.android.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.dotsdev.routine.android.presentation.AppRoute.mainTabRoute

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App(
    appState: AppState = rememberAppState()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { padding ->
        AppNavHost(
            navController = appState.navController,
            navControllerBottomBar = appState.navControllerBottomBar,
            onBackClick = {},
            modifier = Modifier.padding(padding),
            startDestination = mainTabRoute,
        )
    }
}