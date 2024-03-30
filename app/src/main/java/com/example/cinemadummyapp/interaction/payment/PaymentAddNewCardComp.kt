package com.example.cinemadummyapp.interaction.payment

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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.R
import java.util.UUID

@Composable
fun PaymentAddNewCard(
    mainViewModel: MainViewModel,
    returnToCardSelect: () -> Unit = {}
) {
    val cards by mainViewModel.cards.collectAsStateWithLifecycle()
    var newCard by remember {
        mutableStateOf(
            Card(
                id = UUID.randomUUID().toString(),
                cardNumber = "",
                cardDate = "",
                cardHolderName = "",
                cardType = CardType.entries.random(),
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
                val imageVector = when (newCard.cardType) {
                    CardType.Visa -> ImageVector.vectorResource(id = R.drawable.ic_card_visa)
                    CardType.Mastercard -> ImageVector.vectorResource(id = R.drawable.ic_card_master)
                }
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(64.dp),
                    imageVector = imageVector,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = newCard.cardNumber,
                    color = Color.White,
                    textAlign = TextAlign.End,
                )
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
                    value = TextFieldValue(
                        newCard.cardNumber,
                        selection = TextRange(newCard.cardNumber.length),
                    ),
                    onValueChange = {
                        val text = it.text
                        when {
                            // prevent over typing
                            text.length > "1234 1234 1234 1234".length -> {
                            }

                            // prevent pasting text
                            text.length > newCard.cardNumber.length + 1 -> {}

                            text.isBlank() -> {
                                newCard = newCard.copy(cardNumber = "")
                            }

                            text.isEmpty() -> {
                                newCard = newCard.copy(cardNumber = "")
                            }

                            text.last().isDigit() -> {
                                val result = StringBuilder()
                                text.replace(" ", "").chunked(4).forEach {
                                    result.append(it)
                                    result.append(" ")
                                }

                                newCard = newCard.copy(
                                    cardNumber = result.toString().trim(),
                                    cardType = if (result.toString().trim().length < 5)
                                        CardType.entries.random()
                                    else newCard.cardType
                                )
                            }

                            text.last().toString() == " " -> {
                                newCard = newCard.copy(
                                    cardNumber = text.trim(),
                                    cardType = if (text.trim().length < 5)
                                        CardType.entries.random()
                                    else newCard.cardType
                                )
                            }

                            else -> {}
                        }
                    },
                    label = { Text("Card number") },
                    placeholder = {
                        Text(
                            text = "xxxx-xxxx-xxxx-xxxx",
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