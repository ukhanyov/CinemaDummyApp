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
fun HomeScreenPreview() {
    CinemaDummyAppTheme {
        HomeScreen()
    }
}


@Composable
fun HomeScreen() {
    Text(
        text = "HomeScreen",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxSize()
    )
}