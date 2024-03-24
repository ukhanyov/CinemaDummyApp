package com.example.cinemadummyapp.onboarding

import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.biometric.Biometric
import com.example.cinemadummyapp.common.biometric.BiometricPromptManager
import com.example.cinemadummyapp.ui.theme.AppMainAccent

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun OnboardingScreenPreview() {
//    CinemaDummyAppTheme {
//        OnboardingScreen()
//    }
//}

@Composable
fun OnboardingScreen(
    promptManager: BiometricPromptManager,
    goToCreateAccount: () -> Unit = {},
    goToLogin: () -> Unit = {},
    goToUsage: () -> Unit = {},
) {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = AppMainAccent.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppMainAccent)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        ) {
            val painter = painterResource(R.drawable.logo)
            Image(
                modifier = Modifier
                    .aspectRatio(136.dp / 102.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .sizeIn(maxWidth = 136.dp, maxHeight = 102.dp),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp),
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    onClick = { goToCreateAccount() }
                ) {
                    Text(
                        text = "SIGN UP WITH EMAIL",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = "Already have an account?",
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .clickable { goToLogin() },
                    textAlign = TextAlign.Center
                )
            }

            Biometric(
                promptManager = promptManager,
                success = { goToUsage() }
            )
        }
    }
}