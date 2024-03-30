package com.example.cinemadummyapp.interaction.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme
import kotlinx.coroutines.delay

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
fun PaymentProgressComp(
    onSuccess: () -> Unit = {},
    onFailure: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = "Almost there ...",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "Your payment is pending",
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )

        LaunchedEffect(key1 = true) {
            delay(1500)
            onSuccess()
        }
    }
}