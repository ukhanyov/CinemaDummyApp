package com.example.cinemadummyapp.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.Settings.ACTION_BIOMETRIC_ENROLL
import android.provider.Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.biometric.BiometricManager.Authenticators
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.biometric.BiometricPromptManager
import com.example.cinemadummyapp.common.biometric.BiometricPromptManager.BiometricResult
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
                val biometricResult by promptManager.promptResults.collectAsState(initial = null)
                val enrollActivityResult = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = {
                        Toast.makeText(activity, "${it}", Toast.LENGTH_LONG)
                            .show()
                    }
                )
                LaunchedEffect(biometricResult) {
                    if (biometricResult is BiometricResult.AuthenticationNotSet) {
                        if (Build.VERSION.SDK_INT >= 30) {
                            val enrollIntent = Intent(ACTION_BIOMETRIC_ENROLL).apply {
                                putExtra(
                                    EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                    Authenticators.BIOMETRIC_STRONG or Authenticators.DEVICE_CREDENTIAL
                                )
                            }
                            enrollActivityResult.launch(enrollIntent)
                        }
                    }
                }
                Icon(
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp)
                        .clickable {
                            promptManager.showBiometricPrompt("asd", "fgh")
                        },
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_biometric),
                    contentDescription = null
                )
                biometricResult?.let { result ->
                    when (result) {
                        is BiometricResult.AuthenticationError -> {
                            Toast.makeText(activity, result.error, Toast.LENGTH_LONG).show()
                        }

                        BiometricResult.AuthenticationFailed -> {
                            Toast.makeText(activity, "AuthenticationFailed", Toast.LENGTH_LONG)
                                .show()
                        }

                        BiometricResult.AuthenticationNotSet -> {
                            Toast.makeText(activity, "AuthenticationNotSet", Toast.LENGTH_LONG)
                                .show()
                        }

                        BiometricResult.AuthenticationSuccess -> {
                            Toast.makeText(activity, "AuthenticationSuccess", Toast.LENGTH_LONG)
                                .show()
                            goToUsage()
                        }

                        BiometricResult.FeatureUnavailable -> {
                            Toast.makeText(activity, "FeatureUnavailable", Toast.LENGTH_LONG)
                                .show()
                        }

                        BiometricResult.HardwareUnavailable -> {
                            Toast.makeText(activity, "HardwareUnavailable", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }
}