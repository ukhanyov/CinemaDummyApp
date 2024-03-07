package com.example.cinemadummyapp.interaction.tickets

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun TicketsScreenPreview() {
    CinemaDummyAppTheme {
        TicketsScreen()
    }
}


@Composable
fun TicketsScreen() {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.Black.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = false

    Text(
        text = "TicketsScreen",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}