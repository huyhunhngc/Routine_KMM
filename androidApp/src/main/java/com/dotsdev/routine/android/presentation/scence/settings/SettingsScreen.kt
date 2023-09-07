package com.dotsdev.routine.android.presentation.scence.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dotsdev.routine.android.presentation.AppRoute.settings
import com.dotsdev.routine.android.util.Alpha
import com.dotsdev.routine.model.preferences.AppThemeType
import com.dotsdev.routine.resources.MR
import com.dotsdev.routine.resources.stringResource

fun NavController.navigateToSettingsScreen() {
    navigate(settings)
}

fun NavGraphBuilder.settingsScreens(
    onBackClick: () -> Unit
) {
    composable(settings) {
        SettingsScreen(onBackClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val appThemeType by viewModel.appTheme.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(MR.strings.back)
                        )
                    }
                },
                title = {
                    Text(stringResource(MR.strings.settings))
                }
            )
        }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(MR.strings.theme))
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Adaptive(minSize = 96.dp)
                ) {
                    items(AppThemeType.values()) {
                        AppThemeTypeItem(
                            it, onClick = { viewModel.setAppTheme(it) }, it == appThemeType
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppThemeTypeItem(
    AppThemeType: AppThemeType,
    onClick: () -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val selectedAlpha = if (selected) Alpha.High else Alpha.Disabled
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = selectedAlpha)
        ),
        modifier = modifier
            .height(96.dp)
            .fillMaxWidth(),
        tonalElevation = if (selected) 6.dp else 0.dp,
        contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = selectedAlpha)
    ) {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    AppThemeType.themeIcon(), contentDescription = null
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    AppThemeType.themeName(),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
internal fun AppThemeType.themeIcon(): ImageVector = when (this) {
    AppThemeType.FOLLOW_SYSTEM -> Icons.Filled.Contrast
    AppThemeType.DARK_THEME -> Icons.Filled.DarkMode
    AppThemeType.LIGHT_THEME -> Icons.Filled.LightMode
}

@Composable
internal fun AppThemeType.themeName(): String = when (this) {
    AppThemeType.FOLLOW_SYSTEM -> stringResource(MR.strings.follow_system)
    AppThemeType.DARK_THEME -> stringResource(MR.strings.dark_theme)
    AppThemeType.LIGHT_THEME -> stringResource(MR.strings.light_theme)
}

