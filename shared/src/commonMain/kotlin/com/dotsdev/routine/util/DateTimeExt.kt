package com.dotsdev.routine.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

fun Long.toDueDayFormatted(): String {
    val dueDate = Instant.fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.UTC)
    val todayDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val formattedHour = "${dueDate.hour}:${dueDate.minute}h"
    return if (dueDate.date.compareTo(todayDate) == 0) {
        formattedHour
    } else {
        "${dueDate.dayOfMonth}-${dueDate.monthNumber}-${dueDate.year} $formattedHour"
    }
}