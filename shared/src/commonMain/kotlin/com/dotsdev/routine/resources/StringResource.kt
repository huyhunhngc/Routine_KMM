package com.dotsdev.routine.resources

import androidx.compose.runtime.Composable
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun stringResource(resource: StringResource): String =
    stringResource(resource)

@Composable
fun stringResource(resource: StringResource, vararg args: Any): String =
    stringResource(resource, *args)
