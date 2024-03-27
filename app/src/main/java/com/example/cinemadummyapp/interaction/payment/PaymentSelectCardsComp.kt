package com.example.cinemadummyapp.interaction.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.AppMainAccent

@Composable
fun PaymentSelectCardsComp(
    mainViewModel: MainViewModel,
    onBackClicked: () -> Unit = {},
) {
    val cards by mainViewModel.cards.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(toolbarState = ToolbarState.Payment(), onBackClicked = { onBackClicked() })
        LazyColumn(
            verticalArrangement = Arrangement.Top,
        ) {
            items(cards, key = { it.id }) { card ->
                val backgroundColor = when (card.cardType) {
                    CardType.Visa -> Color(0xFFF5F5F5)
                    CardType.Mastercard -> Color(0xFF151B3A)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(backgroundColor)
                        .clickable { mainViewModel.selectCard(card) },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val painter = when (card.cardType) {
                        CardType.Visa -> painterResource(id = R.drawable.ic_card_visa)
                        CardType.Mastercard -> painterResource(id = R.drawable.ic_card_master)
                    }
                    Image(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(64.dp),
                        painter = painter,
                        contentDescription = null,
                    )
                    Column {
                        val header = when (card.cardType2) {
                            CardType2.Credit -> "Credit Card"
                            CardType2.Debit -> "Debit Card"
                        }
                        val textColor = when (card.cardType) {
                            CardType.Visa -> Color.Black
                            CardType.Mastercard -> Color.White
                        }
                        Text(
                            modifier = Modifier.padding(top = 8.dp, bottom = 2.dp),
                            text = header,
                            color = textColor
                        )
                        Text(
                            modifier = Modifier.padding(top = 2.dp, bottom = 8.dp),
                            text = card.cardNumber,
                            color = textColor
                        )
                    }
                    RadioButton(
                        modifier = Modifier.padding(8.dp),
                        selected = card.isSelected,
                        onClick = { mainViewModel.selectCard(card) })
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
            onClick = { },
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                text = "Confirm",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}