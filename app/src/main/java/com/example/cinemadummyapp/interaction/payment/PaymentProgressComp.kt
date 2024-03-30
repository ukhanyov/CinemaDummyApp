package com.example.cinemadummyapp.interaction.payment

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun OnboardingScreenPreview() {
    CinemaDummyAppTheme {
        PaymentProgressComp()
    }
}

@Composable
fun PaymentProgressComp() {

}