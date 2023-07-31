package com.dotsdev.routine.domain.repository.driver

import com.dotsdev.routine.RoutineDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual object DriverFactory {
    actual fun createSqlDriver(): SqlDriver {
        return NativeSqliteDriver(RoutineDatabase.Schema, "app.db")
    }
}