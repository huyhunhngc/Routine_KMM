package com.dotsdev.routine.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.dotsdev.routine.android.util.backgroundColor

@Composable
fun CalendarCardHeader(isAccepted: Boolean, showDate: Boolean) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(Modifier.alpha(if (showDate) 1f else 0f)) {
            DateHeaderItem()
        }
        EventCardInternal(
            isAccepted, Modifier
                .weight(1f)
        )
    }
}

@Composable
fun EventCardInternal(isAccepted: Boolean, modifier: Modifier = Modifier) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (!isAccepted) colorScheme.background else colorScheme.surfaceTint
        ),
        colors = cardColors(if (isAccepted) colorScheme.background else colorScheme.surfaceTint),
    ) {
        Column(Modifier.padding(8.dp)) {
            EventText(
                text = "Jetpack Compose Discussions, Brainstorming",
                style = textStyle(isAccepted)
            )

            EventText(
                text = "5:00 - 5:15 PM",
                style = textStyle(isAccepted)
            )
        }
    }
}

@Composable
fun DateHeaderItem() {
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme
    Column(Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Mon",
            style = typography.titleSmall.copy(colorScheme.surfaceTint)
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
                .background(colorScheme.surfaceTint)
        ) {
            Text(
                text = "28",
                style = typography.titleMedium.copy(colorScheme.onPrimary),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun textStyle(isAccepted: Boolean) =
    if (!isAccepted) MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.onPrimary)
    else MaterialTheme.typography.bodyMedium.copy(MaterialTheme.colorScheme.surfaceTint)

@Composable
fun EventText(text: String, style: TextStyle) {
    Text(
        text = text,
        style = style
    )
}