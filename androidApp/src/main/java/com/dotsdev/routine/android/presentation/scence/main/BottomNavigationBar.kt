package com.dotsdev.routine.android.presentation.scence.main

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dotsdev.routine.android.presentation.AppRoute.calendarRoute
import com.dotsdev.routine.android.presentation.AppRoute.podcastRoute
import com.dotsdev.routine.android.presentation.AppRoute.taskRoute
import com.dotsdev.routine.android.util.backgroundColor

sealed class NavigationItem(
    val route: String,
    val icon: ImageVector,
) {
    object HomeTask : NavigationItem(taskRoute, Icons.Filled.Task)
    object Calendar : NavigationItem(calendarRoute, Icons.Filled.CalendarMonth)
    object Podcast : NavigationItem(podcastRoute, Icons.Filled.Podcasts)
}

val navigationBarItems = listOf(
    NavigationItem.HomeTask,
    NavigationItem.Calendar,
    NavigationItem.Podcast
)

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    items: List<NavigationItem> = navigationBarItems,
) {
    val colorScheme = MaterialTheme.colorScheme
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier.height(50.dp),
        containerColor = colorScheme.backgroundColor(),
        tonalElevation = 1.dp,
    ) {
        items.forEach {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null,
                        modifier = Modifier,
                        tint = colorScheme.surfaceTint
                    )
                },
                modifier = Modifier.padding(1.dp),
                alwaysShowLabel = false,
                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}