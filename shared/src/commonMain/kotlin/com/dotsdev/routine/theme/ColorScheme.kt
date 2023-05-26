package com.dotsdev.routine.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import com.dotsdev.routine.theme.amberDark
import com.dotsdev.routine.theme.amberLight
import com.dotsdev.routine.theme.blueDark
import com.dotsdev.routine.theme.blueLight
import com.dotsdev.routine.theme.brownDark
import com.dotsdev.routine.theme.brownLight
import com.dotsdev.routine.theme.grayDark
import com.dotsdev.routine.theme.grayLight
import com.dotsdev.routine.theme.greenDark
import com.dotsdev.routine.theme.greenLight
import com.dotsdev.routine.theme.indigoDark
import com.dotsdev.routine.theme.indigoLight
import com.dotsdev.routine.theme.limeDark
import com.dotsdev.routine.theme.limeLight
import com.dotsdev.routine.theme.orangeDark
import com.dotsdev.routine.theme.orangeLight
import com.dotsdev.routine.theme.pinkDark
import com.dotsdev.routine.theme.pinkLight
import com.dotsdev.routine.theme.redDark
import com.dotsdev.routine.theme.redLight
import com.dotsdev.routine.theme.tealDark
import com.dotsdev.routine.theme.tealLight
import com.dotsdev.routine.theme.routine_dark_background
import com.dotsdev.routine.theme.routine_dark_error
import com.dotsdev.routine.theme.routine_dark_errorContainer
import com.dotsdev.routine.theme.routine_dark_inverseOnSurface
import com.dotsdev.routine.theme.routine_dark_inversePrimary
import com.dotsdev.routine.theme.routine_dark_inverseSurface
import com.dotsdev.routine.theme.routine_dark_onBackground
import com.dotsdev.routine.theme.routine_dark_onError
import com.dotsdev.routine.theme.routine_dark_onErrorContainer
import com.dotsdev.routine.theme.routine_dark_onPrimary
import com.dotsdev.routine.theme.routine_dark_onPrimaryContainer
import com.dotsdev.routine.theme.routine_dark_onSecondary
import com.dotsdev.routine.theme.routine_dark_onSecondaryContainer
import com.dotsdev.routine.theme.routine_dark_onSurface
import com.dotsdev.routine.theme.routine_dark_onSurfaceVariant
import com.dotsdev.routine.theme.routine_dark_onTertiary
import com.dotsdev.routine.theme.routine_dark_onTertiaryContainer
import com.dotsdev.routine.theme.routine_dark_outline
import com.dotsdev.routine.theme.routine_dark_primary
import com.dotsdev.routine.theme.routine_dark_primaryContainer
import com.dotsdev.routine.theme.routine_dark_secondary
import com.dotsdev.routine.theme.routine_dark_secondaryContainer
import com.dotsdev.routine.theme.routine_dark_surface
import com.dotsdev.routine.theme.routine_dark_surfaceTint
import com.dotsdev.routine.theme.routine_dark_surfaceVariant
import com.dotsdev.routine.theme.routine_dark_tertiary
import com.dotsdev.routine.theme.routine_dark_tertiaryContainer
import com.dotsdev.routine.theme.routine_light_background
import com.dotsdev.routine.theme.routine_light_error
import com.dotsdev.routine.theme.routine_light_errorContainer
import com.dotsdev.routine.theme.routine_light_inverseOnSurface
import com.dotsdev.routine.theme.routine_light_inversePrimary
import com.dotsdev.routine.theme.routine_light_inverseSurface
import com.dotsdev.routine.theme.routine_light_onBackground
import com.dotsdev.routine.theme.routine_light_onError
import com.dotsdev.routine.theme.routine_light_onErrorContainer
import com.dotsdev.routine.theme.routine_light_onPrimary
import com.dotsdev.routine.theme.routine_light_onPrimaryContainer
import com.dotsdev.routine.theme.routine_light_onSecondary
import com.dotsdev.routine.theme.routine_light_onSecondaryContainer
import com.dotsdev.routine.theme.routine_light_onSurface
import com.dotsdev.routine.theme.routine_light_onSurfaceVariant
import com.dotsdev.routine.theme.routine_light_onTertiary
import com.dotsdev.routine.theme.routine_light_onTertiaryContainer
import com.dotsdev.routine.theme.routine_light_outline
import com.dotsdev.routine.theme.routine_light_primary
import com.dotsdev.routine.theme.routine_light_primaryContainer
import com.dotsdev.routine.theme.routine_light_secondary
import com.dotsdev.routine.theme.routine_light_secondaryContainer
import com.dotsdev.routine.theme.routine_light_surface
import com.dotsdev.routine.theme.routine_light_surfaceTint
import com.dotsdev.routine.theme.routine_light_surfaceVariant
import com.dotsdev.routine.theme.routine_light_tertiary
import com.dotsdev.routine.theme.routine_light_tertiaryContainer
import com.dotsdev.routine.theme.routine_secondary50
import com.dotsdev.routine.theme.routine_secondary80
import com.dotsdev.routine.theme.yellowDark
import com.dotsdev.routine.theme.yellowLight

val LightColorScheme = lightColorScheme(
    primary = routine_light_primary,
    onPrimary = routine_light_onPrimary,
    primaryContainer = routine_light_primaryContainer,
    onPrimaryContainer = routine_light_onPrimaryContainer,
    secondary = routine_light_secondary,
    onSecondary = routine_light_onSecondary,
    secondaryContainer = routine_light_secondaryContainer,
    onSecondaryContainer = routine_light_onSecondaryContainer,
    tertiary = routine_light_tertiary,
    onTertiary = routine_light_onTertiary,
    tertiaryContainer = routine_light_tertiaryContainer,
    onTertiaryContainer = routine_light_onTertiaryContainer,
    error = routine_light_error,
    errorContainer = routine_light_errorContainer,
    onError = routine_light_onError,
    onErrorContainer = routine_light_onErrorContainer,
    background = routine_light_background,
    onBackground = routine_light_onBackground,
    surface = routine_light_surface,
    onSurface = routine_light_onSurface,
    surfaceVariant = routine_light_surfaceVariant,
    onSurfaceVariant = routine_light_onSurfaceVariant,
    outline = routine_light_outline,
    inverseOnSurface = routine_light_inverseOnSurface,
    inverseSurface = routine_light_inverseSurface,
    inversePrimary = routine_light_inversePrimary,
    surfaceTint = routine_light_surfaceTint
)

val ToDometerLightColors = lightColors(
    check = routine_secondary50,
    amber = amberLight,
    blue = blueLight,
    brown = brownLight,
    gray = grayLight,
    green = greenLight,
    indigo = indigoLight,
    lime = limeLight,
    orange = orangeLight,
    red = redLight,
    pink = pinkLight,
    teal = tealLight,
    yellow = yellowLight
)

val DarkColorScheme = darkColorScheme(
    primary = routine_dark_primary,
    onPrimary = routine_dark_onPrimary,
    primaryContainer = routine_dark_primaryContainer,
    onPrimaryContainer = routine_dark_onPrimaryContainer,
    secondary = routine_dark_secondary,
    onSecondary = routine_dark_onSecondary,
    secondaryContainer = routine_dark_secondaryContainer,
    onSecondaryContainer = routine_dark_onSecondaryContainer,
    tertiary = routine_dark_tertiary,
    onTertiary = routine_dark_onTertiary,
    tertiaryContainer = routine_dark_tertiaryContainer,
    onTertiaryContainer = routine_dark_onTertiaryContainer,
    error = routine_dark_error,
    errorContainer = routine_dark_errorContainer,
    onError = routine_dark_onError,
    onErrorContainer = routine_dark_onErrorContainer,
    background = routine_dark_background,
    onBackground = routine_dark_onBackground,
    surface = routine_dark_surface,
    onSurface = routine_dark_onSurface,
    surfaceVariant = routine_dark_surfaceVariant,
    onSurfaceVariant = routine_dark_onSurfaceVariant,
    outline = routine_dark_outline,
    inverseOnSurface = routine_dark_inverseOnSurface,
    inverseSurface = routine_dark_inverseSurface,
    inversePrimary = routine_dark_inversePrimary,
    surfaceTint = routine_dark_surfaceTint
)

val ToDometerDarkColors = darkColors(
    check = routine_secondary80,
    amber = amberDark,
    blue = blueDark,
    brown = brownDark,
    gray = grayDark,
    green = greenDark,
    indigo = indigoDark,
    lime = limeDark,
    orange = orangeDark,
    red = redDark,
    pink = pinkDark,
    teal = tealDark,
    yellow = yellowDark
)
