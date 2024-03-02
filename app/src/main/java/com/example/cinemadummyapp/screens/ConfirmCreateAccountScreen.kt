package com.example.cinemadummyapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@Composable
fun ConfirmCreateAccountScreenPreview() {
    CinemaDummyAppTheme {
        ConfirmCreateAccountScreen()
    }
}

@Composable
fun ConfirmCreateAccountScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

    }
}