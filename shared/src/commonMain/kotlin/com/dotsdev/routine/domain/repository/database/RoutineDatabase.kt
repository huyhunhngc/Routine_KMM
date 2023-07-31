package com.dotsdev.routine.domain.repository.database

import com.dotsdev.routine.RoutineDatabase
import com.dotsdev.routine.TaskChecklistItemEntity
import com.dotsdev.routine.TaskEntity
import com.dotsdev.routine.domain.repository.driver.DriverFactory
import com.squareup.sqldelight.EnumColumnAdapter

fun createDatabase(): RoutineDatabase =
    RoutineDatabase(
        DriverFactory.createSqlDriver(),
        TaskEntityAdapter = TaskEntity.Adapter(
            stateAdapter = EnumColumnAdapter(),
            tagAdapter = EnumColumnAdapter()
        ),
        TaskChecklistItemEntityAdapter = TaskChecklistItemEntity.Adapter(
            stateAdapter = EnumColumnAdapter()
        )
    )