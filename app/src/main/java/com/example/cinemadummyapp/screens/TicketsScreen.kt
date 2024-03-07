package com.example.cinemadummyapp.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
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
    Text(
        text = "TicketsScreen",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}