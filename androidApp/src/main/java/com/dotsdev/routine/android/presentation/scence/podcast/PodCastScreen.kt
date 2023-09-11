package com.dotsdev.routine.android.presentation.scence.podcast

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute

fun NavGraphBuilder.podCastScreens() {
    composable(AppRoute.podcastRoute) {
        PodCastScreen()
    }
}

@Composable
fun PodCastScreen() {
}