package com.example.cinemadummyapp.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.common.tickets.Ticket
import com.example.cinemadummyapp.common.tickets.TicketState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBottomSheet(
    cart: List<Ticket>,
    onDismiss: () -> Unit,
    onSuccess: () -> Unit,
    removeItem: (Ticket) -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        contentColor = Color.White,
        containerColor = Color.White,
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        if (cart.isEmpty()) onDismiss()
        CartTickets(cart = cart, removeItem = { removeItem(it) }, onSuccess = { onSuccess() })
    }
}

@Composable
fun CartTickets(
    cart: List<Ticket>,
    removeItem: (Ticket) -> Unit,
    onSuccess: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(cart + listOf(
            Ticket(
                id = "Sum row",
                date = "",
                time = "",
                movie = randomMovie,
                row = 0,
                seat = "",
                ticketState = TicketState.Selected,
                price = 0,
            )
        ), key = { it.id }) { ticket ->
            if (ticket.id == "Sum row") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                ) {
                    Text(
                        text = "Total: $${cart.sumOf { it.price }}",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
                    Button(
                        modifier = Modifier.padding(vertical = 8.dp),
                        shape = RoundedCornerShape(10.dp),
                        onClick = { onSuccess() },
                    ) {
                        Text(
                            text = "To payment",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 14.sp,
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier
                            .size(width = 120.dp, height = 180.dp)
                            .padding(16.dp)
                            .weight(1f)
                            .clip(RoundedCornerShape(16.dp)),
                        painter = painterResource(ticket.movie.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .weight(2f)
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                            Text(
                                text = "${ticket.date} - ${ticket.time}",
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                        }
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ticket_logo_cart_sheet),
                                contentDescription = null,
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                            Text(
                                text = "Seat: ${ticket.row}${ticket.seat}",
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                        }
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Filled.ShoppingCart,
                                contentDescription = null,
                                tint = Color.Black,
                            )
                            Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                            Text(
                                text = "$${ticket.price}",
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                        }
                        Button(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(10.dp),
                            onClick = { removeItem(ticket) },
                        ) {
                            Text(
                                text = "Remove",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                            )
                        }
                    }
                }
                HorizontalDivider()
            }
        }
    }
}