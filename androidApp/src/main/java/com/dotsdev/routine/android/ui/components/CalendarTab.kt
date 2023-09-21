package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun CalendarTabIndicator(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .zIndex(-1f)
            .padding(horizontal = tabIndicatorHorizontalGap / 2)
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(50),
            ),
    )
}

@Composable
fun CalendarTimetableTabRow(
    tabState: TimetableTabState,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    indicator: @Composable (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
        if (selectedTabIndex < tabPositions.size) {
            CalendarTabIndicator(
                Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
            )
        }
    },
    tabs: @Composable () -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
            .defaultMinSize(minHeight = tabRowMaxHeight - ((tabRowMaxHeight - tabRowMinHeight) * tabState.tabCollapseProgress))
            .padding(
                start = tabRowHorizontalSpacing,
                top = tabRowTopSpacing,
                end = tabRowHorizontalSpacing,
                bottom = tabRowBottomSpacing,
            ),
        divider = {},
        indicator = indicator,
        tabs = tabs,
    )
}

@Stable
class TimetableTabState(
    initialOffsetLimit: Float = 0f,
    initialScrollOffset: Float = 0f,
) {

    private val scrollOffsetLimit by mutableFloatStateOf(initialOffsetLimit)

    val tabCollapseProgress: Float
        get() = scrollOffset / scrollOffsetLimit

    private val _scrollOffset = mutableFloatStateOf(initialScrollOffset)

    var scrollOffset: Float
        get() = _scrollOffset.value
        private set(newOffset) {
            _scrollOffset.value = newOffset.coerceIn(
                minimumValue = scrollOffsetLimit,
                maximumValue = 0f,
            )
        }

    val isTabExpandable: Boolean
        get() = scrollOffset > scrollOffsetLimit

    val isTabCollapsing: Boolean
        get() = scrollOffset != 0f

    fun onScroll(y: Float) {
        scrollOffset += y
    }

    companion object {
        val Saver: Saver<TimetableTabState, *> = listSaver(
            save = { listOf(it.scrollOffsetLimit, it.scrollOffset) },
            restore = {
                TimetableTabState(
                    initialOffsetLimit = it[0],
                    initialScrollOffset = it[1],
                )
            },
        )
    }
}

private val tabMinHeight = 32.dp
private val tabMaxHeight = 56.dp

private val tabIndicatorHorizontalGap = 8.dp

private val tabRowHorizontalSpacing = 16.dp - (tabIndicatorHorizontalGap / 2)
private val tabRowTopSpacing = 16.dp
private val tabRowBottomSpacing = 12.dp
private val tabRowMinHeight = tabMinHeight + tabRowTopSpacing + tabRowBottomSpacing
private val tabRowMaxHeight = tabMaxHeight + tabRowTopSpacing + tabRowBottomSpacing