package com.example.cinemadummyapp.interaction.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemadummyapp.MainViewModel

@Composable
fun PaymentAddNewCard(
    mainViewModel: MainViewModel,
    returnToCardSelect: () -> Unit = {}
) {
    val cards by mainViewModel.cards.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(1f),
                shape = RoundedCornerShape(10.dp),
                onClick = { returnToCardSelect() },
            ) {
                Text(
                    text = "Back",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .weight(1f),
                shape = RoundedCornerShape(10.dp),
                enabled = false,
                onClick = { },
            ) {
                Text(
                    text = "Confirm",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}