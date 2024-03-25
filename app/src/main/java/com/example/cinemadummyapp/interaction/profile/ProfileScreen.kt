package com.example.cinemadummyapp.interaction.profile

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun ProfileScreenPreview() {
    CinemaDummyAppTheme {
        ProfileScreen()
    }
}


@Composable
fun ProfileScreen(
    onBackClicked: () -> Unit = {},
    onProfileDeleted: () -> Unit = {}
) {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.White.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = true

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 36.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(
            toolbarState = ToolbarState.Profile(),
            onBackClicked = { onBackClicked() }
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 16.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = { onProfileDeleted() },
        ) {
            Text(
                text = "Delete Account",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }
    }
}