package com.example.cinemadummyapp.common.toolbar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun HomeToolbarPreview() {
    CinemaDummyAppTheme {
        HomeToolbar(ToolbarState.Home())
    }
}

@Composable
fun HomeToolbar(state: ToolbarState.Home) {
    Text(
        text = state.title,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    )
}