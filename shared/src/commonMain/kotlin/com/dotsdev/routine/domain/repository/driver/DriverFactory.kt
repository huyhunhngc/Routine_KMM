package com.dotsdev.routine.domain.repository.driver

import com.squareup.sqldelight.db.SqlDriver

expect object DriverFactory {
    fun createSqlDriver(): SqlDriver
}