package com.dotsdev.routine.android.presentation.scence.main

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val route: String,
    @StringRes val title: Int,
    val icon: ImageVector,
) {
//    object Feed : BottomNavigationItem(home, R.string.feed, Icons.Filled.Home)
//    object Trending : BottomNavigationItem(trendRoute, R.string.trending, Icons.Filled.TrendingUp)
//    object BookMark : BottomNavigationItem(bookMarkRoute, R.string.book_mark, Icons.Filled.Bookmark)
}
