package com.example.cinemadummyapp.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

sealed class ToolbarState {
    data class Home(
        val title: String = "Movies"
    ) : ToolbarState()
}

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    CinemaDummyAppTheme {
        Toolbar()
    }
}

@Composable
fun Toolbar(
    toolbarState: ToolbarState = ToolbarState.Home()
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        when (toolbarState) {
            is ToolbarState.Home -> {
                Text(
                    text = toolbarState.title,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
}