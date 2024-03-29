package com.example.cinemadummyapp.interaction.payment

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var newCard by remember {
        mutableStateOf<Card?>(
            Card(
                id = "Add card",
                cardNumber = "1234 4321 1234 4321",
                cardDate = "31/09",
                cardHolderName = "Your Name",
                cardType = CardType.Mastercard,
            )
        )
    }
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
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .aspectRatio(154.dp / 85.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
            ) {
//                val imageVector = when (card.cardType) {
//                    CardType.Visa -> ImageVector.vectorResource(id = R.drawable.ic_card_visa)
//                    CardType.Mastercard -> ImageVector.vectorResource(id = R.drawable.ic_card_master)
//                }
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_card_master),
                    contentDescription = null,
                )
                newCard?.cardNumber?.let { cardNumber ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        cardNumber.split(" ").forEach {
                            Text(
                                text = it,
                                color = Color.White
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Column {
                        Text(
                            text = "CARD HOLDER",
                            color = Color.White
                        )
                        Text(
                            text = "Name",
                            color = Color.White
                        )
                    }
                    Column {
                        Text(
                            text = "EXPIRES",
                            color = Color.White
                        )
                        Text(
                            text = "31/9",
                            color = Color.White
                        )
                    }
                }
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = newCard?.cardNumber ?: "",
                    onValueChange = {
                        if (it.isNotEmpty() && it.last().toString() == " ") {
                            newCard = newCard?.copy(cardNumber = it.trim())
                        } else if (it.isNotEmpty() && it.last().isDigit()) {
                            Log.d("my_tag", "start -> $it")
                            val inputText = it.replace(" ", "")
                            Log.d("my_tag", "inputText -> $inputText")
                            if (inputText.length <= 16) {
                                Log.d(
                                    "my_tag",
                                    "formatCardNumber -> ${formatCardNumber(inputText)}"
                                )
                                newCard = newCard?.copy(cardNumber = formatCardNumber(inputText))
                            }
                            Log.d("my_tag", "----------------")
                        } else if (it.isBlank() || it.isEmpty()) {
                            newCard = newCard?.copy(cardNumber = "")
                        }
                    },
                    label = { Text("Card number") },
                    placeholder = {
                        Text(
                            text = "xxxx-xxxx-xxxx-2024",
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
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
            }
        }
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

private fun formatCardNumber(inputString: String): String {
    // Ensure the initial string is 16 characters long
//    if (inputString.length != 16) return inputString

    return inputString.chunked(4).joinToString(" ")
}