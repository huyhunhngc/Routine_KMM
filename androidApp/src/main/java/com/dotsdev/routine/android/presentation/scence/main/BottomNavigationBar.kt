package com.dotsdev.routine.android.presentation.scence.main

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.material.icons.filled.Task
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dotsdev.routine.android.R
import com.dotsdev.routine.android.presentation.AppRoute.calendarRoute
import com.dotsdev.routine.android.presentation.AppRoute.podcastRoute
import com.dotsdev.routine.android.presentation.AppRoute.taskRoute

sealed class NavigationItem(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
) {
    object HomeTask : NavigationItem(taskRoute, R.string.feed, Icons.Filled.Task)
    object Calendar : NavigationItem(calendarRoute, R.string.trending, Icons.Filled.CalendarMonth)
    object Podcast : NavigationItem(podcastRoute, R.string.book_mark, Icons.Filled.Podcasts)
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
    val colorScheme = MaterialTheme.colors
    val typography = MaterialTheme.typography
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        modifier = modifier,
        backgroundColor = colorScheme.background,
        contentColor = colorScheme.onBackground,
        elevation = 1.dp,
    ) {
        items.forEach {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = stringResource(it.title),
                        modifier = Modifier
                    )
                },
                label = {
                    Text(text = stringResource(it.title), style = typography.body2)
                },
                alwaysShowLabel = true,
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