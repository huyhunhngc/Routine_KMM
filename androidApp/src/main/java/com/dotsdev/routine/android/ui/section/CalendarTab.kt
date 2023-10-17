package com.dotsdev.routine.android.ui.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dotsdev.routine.ui.components.textStyle
import com.dotsdev.routine.model.WeekDay
import kotlin.math.roundToInt

@Composable
fun CalendarTabHeader(
    tabState: CalendarTabState,
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

@Composable
fun CalendarTabItem(
    day: WeekDay,
    selected: Boolean,
    onClick: () -> Unit,
    scrollState: CalendarTabState,
    modifier: Modifier = Modifier,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(6.dp),
            ) {
                Text(
                    text = "D${day.index}",
                    style = textStyle(!selected),
                    modifier = Modifier
                        .graphicsLayer {
                            alpha = (1 - scrollState.tabCollapseProgress * 2).coerceAtLeast(0f)
                        }
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            layout(
                                placeable.width,
                                placeable.height - (placeable.height * scrollState.tabCollapseProgress).roundToInt(),
                            ) {
                                placeable.placeRelative(0, 0)
                            }
                        },
                )
                Text(
                    text = day.name.substring(0, 3),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        selectedContentColor = MaterialTheme.colorScheme.onPrimary,
        unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = modifier.heightIn(min = tabMinHeight),
    )
}
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
fun rememberCalendarTabContentScrollState(
    tabScrollState: CalendarTabState = rememberCalendarTabState(),
): CalendarTabContentScrollState {
    return remember { CalendarTabContentScrollState(tabScrollState) }
}

@Stable
class CalendarTabContentScrollState(
    val tabScrollState: CalendarTabState,
) {
    val nestedScrollConnection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            return onPreScrollSheetContent(available)
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource,
        ): Offset {
            return onPostScrollSheetContent(available)
        }
    }

    /**
     * @return consumed offset
     */
    private fun onPreScrollSheetContent(availableScrollOffset: Offset): Offset {
        if (availableScrollOffset.y >= 0) return Offset.Zero
        // When scrolled upward
        return if (tabScrollState.isTabExpandable) {
            val prevHeightOffset: Float = tabScrollState.scrollOffset
            tabScrollState.onScroll(availableScrollOffset.y)
            availableScrollOffset.copy(x = 0f, y = tabScrollState.scrollOffset - prevHeightOffset)
        } else {
            Offset.Zero
        }
    }

    /**
     * @return consumed offset
     */
    private fun onPostScrollSheetContent(availableScrollOffset: Offset): Offset {
        if (availableScrollOffset.y < 0f) return Offset.Zero
        return if (tabScrollState.isTabCollapsing && availableScrollOffset.y > 0) {
            // When scrolling downward and overscroll
            val prevHeightOffset = tabScrollState.scrollOffset
            tabScrollState.onScroll(availableScrollOffset.y)
            availableScrollOffset.copy(x = 0f, y = tabScrollState.scrollOffset - prevHeightOffset)
        } else {
            Offset.Zero
        }
    }
}

@Composable
fun rememberCalendarTabState(initialScrollOffset: Float = 0.0f): CalendarTabState {
    val offsetLimit = LocalDensity.current.run {
        (tabRowMaxHeight - tabRowMinHeight).toPx()
    }
    return rememberSaveable(saver = CalendarTabState.Saver) {
        CalendarTabState(
            initialScrollOffset = initialScrollOffset,
            initialOffsetLimit = -offsetLimit,
        )
    }
}

@Stable
class CalendarTabState(
    initialOffsetLimit: Float = 0f,
    initialScrollOffset: Float = 0f,
) {

    private val scrollOffsetLimit: Float by mutableFloatStateOf(initialOffsetLimit)

    val tabCollapseProgress: Float
        get() = scrollOffset / scrollOffsetLimit

    private val _scrollOffset = mutableFloatStateOf(initialScrollOffset)
    var scrollOffset: Float
        get() = _scrollOffset.floatValue
        private set(newOffset) {
            _scrollOffset.floatValue = newOffset.coerceIn(
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
        val Saver: Saver<CalendarTabState, *> = listSaver(
            save = { listOf(it.scrollOffsetLimit, it.scrollOffset) },
            restore = {
                CalendarTabState(
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