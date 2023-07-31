package com.dotsdev.routine.domain.repository.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dotsdev.routine.RoutineDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual object DriverFactory {
    lateinit var applicationContext: Context
    actual fun createSqlDriver(): SqlDriver {
        return AndroidSqliteDriver(
            RoutineDatabase.Schema,
            applicationContext,
            "app.db",
            callback = object : AndroidSqliteDriver.Callback(RoutineDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.execSQL("PRAGMA foreign_keys = ON;")
                }
            }
        )
    }
}