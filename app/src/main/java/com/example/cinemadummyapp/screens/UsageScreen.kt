package com.example.cinemadummyapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun UsageScreenPreview() {
    CinemaDummyAppTheme {
        UsageScreen()
    }
}

@Composable
fun UsageScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppMainAccent)
    ) {

    }
}