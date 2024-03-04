package com.example.cinemadummyapp.screens

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.isValidEmail
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@Composable
fun LoginScreenPreview() {
    CinemaDummyAppTheme {
        LoginScreen()
    }
}

@Composable
fun LoginScreen(onCreateAccountClicked: () -> Unit = {}) {
    var isEmailValid by rememberSaveable {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val painter = painterResource(R.drawable.logo2)
        Image(
            modifier = Modifier
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .padding(45.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var email by rememberSaveable {
                mutableStateOf("")
            }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text(text = "example@email.com") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = isValidEmail(email).not().also {
                    isEmailValid = it
                }
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
                onClick = { onCreateAccountClicked() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp),
                enabled = email.isNotBlank() && isValidEmail(email)
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}