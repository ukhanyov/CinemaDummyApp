package com.example.cinemadummyapp

import CinemaNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.compose.rememberNavController
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaApp()
        }
    }
}

@Composable
fun CinemaApp() {
    CinemaDummyAppTheme(dynamicColor = false) {
        val navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            CinemaNavHost(navController = navController)
        }
    }
}

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun CinemaAppPreview() {
    CinemaApp()
}