package com.example.cinemadummyapp.onboarding

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.isValidEmail
import com.example.cinemadummyapp.common.isValidPassword
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun LoginScreenPreview() {
    CinemaDummyAppTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(goToAppUsage: () -> Unit = {}) {
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
                .weight(0.5f)
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
                var email by rememberSaveable {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    placeholder = {
                        Text(
                            text = "example@email.com",
                            color = Color.LightGray
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledSupportingTextColor = Color.Black,
                        errorTextColor = Color.Red,
                        errorSupportingTextColor = Color.Red,
                        focusedTextColor = Color.Black,
                        focusedSupportingTextColor = Color.Black,
                        unfocusedSupportingTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = email.isValidEmail().not()
                )

                Spacer(modifier = Modifier.size(16.dp))

                var password by rememberSaveable {
                    mutableStateOf("")
                }
                var isPasswordHidden by rememberSaveable {
                    mutableStateOf(true)
                }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (isPasswordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = Color.Black,
                        disabledSupportingTextColor = Color.Black,
                        errorTextColor = Color.Red,
                        errorSupportingTextColor = Color.Red,
                        focusedTextColor = Color.Black,
                        focusedSupportingTextColor = Color.Black,
                        unfocusedSupportingTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                    ),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordHidden = isPasswordHidden.not() }) {
                            val imageVector = if (isPasswordHidden)
                                ImageVector.vectorResource(id = R.drawable.ic_visibility_off)
                            else
                                ImageVector.vectorResource(id = R.drawable.ic_visibility)
                            Icon(imageVector = imageVector, contentDescription = null)
                        }
                    }
                )

                Spacer(modifier = Modifier.size(32.dp))

                Button(
                    onClick = { goToAppUsage() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
                    modifier = Modifier
                        .sizeIn(minWidth = 100.dp),
                    enabled = email.isNotBlank() && email.isValidEmail() && password.isValidPassword()
                ) {
                    Text(
                        text = "LOGIN",
                        color = Color.White
                    )
                }
            }
        }
    }
}