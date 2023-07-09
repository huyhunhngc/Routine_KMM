package com.dotsdev.routine.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.dotsdev.routine.R

internal val googlesansLightFont = Font(R.font.googlesansregular, FontWeight.Light)
internal val googlesansRegularFont = Font(R.font.googlesansregular, FontWeight.Normal)
internal val googlesansMediumFont = Font(R.font.googlesanstextregular, FontWeight.Medium)
internal val googlesansSemiBoldFont = Font(R.font.googlesanstextmedium, FontWeight.SemiBold)
internal val googlesansBoldFont = Font(R.font.googlesanstextmedium, FontWeight.Bold)

actual val googleFont = FontFamily(
    googlesansLightFont,
    googlesansRegularFont,
    googlesansMediumFont,
    googlesansSemiBoldFont,
    googlesansBoldFont
)
