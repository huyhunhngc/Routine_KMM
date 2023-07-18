package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedBorderItem(
    modifier: Modifier = Modifier,
    border: BorderStroke = DefaultsBorder.borderStroke,
    color: Color = DefaultsBorder.color,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        border = border,
        shape = RoundedCornerShape(14.dp),
        color = color
    ) {
        Row(
            modifier = Modifier.padding(PaddingValues(6.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

private object DefaultsBorder {
    val borderStroke: BorderStroke
        @Composable get() = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        )

    val color: Color @Composable get() = MaterialTheme.colorScheme.surface
}