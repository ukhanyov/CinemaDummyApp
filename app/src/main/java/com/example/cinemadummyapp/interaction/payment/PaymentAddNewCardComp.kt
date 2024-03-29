package com.example.cinemadummyapp.interaction.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.R

@Composable
fun PaymentAddNewCard(
    mainViewModel: MainViewModel,
    returnToCardSelect: () -> Unit = {}
) {
    val cards by mainViewModel.cards.collectAsStateWithLifecycle()
    var newCard by remember { mutableStateOf<Card?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .aspectRatio(154.dp / 85.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.image_new_card),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Box(modifier = Modifier.weight(1f)) {}
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