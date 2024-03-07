package com.example.cinemadummyapp.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun ConfirmCreateAccountScreenPreview() {
    CinemaDummyAppTheme {
        ConfirmCreateAccountScreen()
    }
}

@Composable
fun ConfirmCreateAccountScreen(
    goToAppUsage: () -> Unit = {}
) {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.White.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = true

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .weight(0.75f)
                .align(Alignment.CenterHorizontally)
        ) {
            val painter = painterResource(R.drawable.logo2)
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

                var code by rememberSaveable {
                    mutableStateOf("— — — —")
                }

                Text(
                    text = "Verify Code",
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Please check your email. We just sent a verification code on your email",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))

                TextField(
                    value = code,
                    onValueChange = {
                        val newString = it.replace(" ", "").replace("—", "")
                        if (newString.isDigitsOnly()) {
                            if (newString.isEmpty()) {
                                code = "— — — —"
                                return@TextField
                            }
                            if (newString.length <= 4) code = newString
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    colors = TextFieldDefaults.colors(
                        errorContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    modifier = Modifier
                        .widthIn(min = 100.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))

                Row {
                    Text(
                        text = "Didn’t get a code?",
                        fontSize = 12.sp
                    )
                    Text(
                        text = " Try again",
                        color = AppMainAccent,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))

                Button(
                    onClick = { goToAppUsage() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
                    modifier = Modifier.size(width = 150.dp, height = 36.dp),
                    enabled = code.length == 4
                ) {
                    Text(
                        text = "VERIFY",
                        color = Color.White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .weight(0.25f)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "By signing up you have agreed to our",
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Terms of Use & Privacy Policy",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}