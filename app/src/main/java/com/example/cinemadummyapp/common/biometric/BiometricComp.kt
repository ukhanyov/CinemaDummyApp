package com.example.cinemadummyapp.common.biometric

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun BiometricPreview() {
    CinemaDummyAppTheme {
        val activity = LocalView.current.context as Activity
        Biometric(
            modifier = Modifier,
            promptManager = BiometricPromptManager(activity as AppCompatActivity),
            success = {}
        )
    }
}

@Composable
fun Biometric(
    modifier: Modifier = Modifier,
    promptManager: BiometricPromptManager,
    success: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clickable { promptManager.showBiometricPrompt("Login", "via biometrics") },
    ) {
        val activity = LocalView.current.context as Activity
        val biometricResult by promptManager.promptResults.collectAsState(initial = null)
        val enrollActivityResult = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = { Toast.makeText(activity, "$it", Toast.LENGTH_SHORT).show() }
        )
        LaunchedEffect(biometricResult) {
            if (biometricResult is BiometricPromptManager.BiometricResult.AuthenticationNotSet) {
                if (Build.VERSION.SDK_INT >= 30) {
                    val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                        putExtra(
                            Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                            BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL
                        )
                    }
                    enrollActivityResult.launch(enrollIntent)
                }
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_biometric),
            tint = Color.White,
            contentDescription = null
        )
        biometricResult?.let { result ->
            when (result) {
                is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                    Toast.makeText(activity, "AuthenticationError", Toast.LENGTH_SHORT).show()
                }

                BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                    Toast.makeText(activity, "AuthenticationFailed", Toast.LENGTH_SHORT).show()
                }

                BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                    Toast.makeText(activity, "AuthenticationNotSet", Toast.LENGTH_SHORT).show()
                }

                BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                    success()
                }

                BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                    Toast.makeText(activity, "FeatureUnavailable", Toast.LENGTH_SHORT).show()
                }

                BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                    Toast.makeText(activity, "HardwareUnavailable", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}