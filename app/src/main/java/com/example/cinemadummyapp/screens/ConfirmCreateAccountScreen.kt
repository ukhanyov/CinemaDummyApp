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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@Composable
fun ConfirmCreateAccountScreenPreview() {
    CinemaDummyAppTheme {
        ConfirmCreateAccountScreen()
    }
}

@Composable
fun ConfirmCreateAccountScreen() {
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

            var code by rememberSaveable {
                mutableStateOf("")
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
                    if (it.length <= 4) code = it
                },
                placeholder = { Text(text = "— — — —") },
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
                    .width(100.dp)
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
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
            ) {
                Text(text = "VERIFY")
            }
        }
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
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Terms of Use & Privacy Policy",
                fontSize = 12.sp
            )
        }
    }
}