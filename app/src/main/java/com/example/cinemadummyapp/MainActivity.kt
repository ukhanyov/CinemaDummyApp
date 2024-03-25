package com.example.cinemadummyapp

import CinemaNavHost
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cinemadummyapp.common.biometric.BiometricPromptManager
import com.example.cinemadummyapp.onboarding.Onboarding
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

class MainActivity : AppCompatActivity() {

    private val promptManager by lazy {
        BiometricPromptManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaApp(promptManager)
        }
    }
}

@Composable
fun CinemaApp(
    promptManager: BiometricPromptManager,
    mainViewModel: MainViewModel = viewModel(),
) {
    CinemaDummyAppTheme(dynamicColor = false) {
        val navController = rememberNavController()
        Box(modifier = Modifier.fillMaxSize()) {
            CinemaNavHost(
                navController = navController,
                startDestination = Onboarding.route,
//                startDestination = Usage.route,
                promptManager = promptManager,
                goToCheckout = { mainViewModel.addToCart(it) }
            )
        }
    }
}

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun CinemaAppPreview() {
//    CinemaApp()
//}