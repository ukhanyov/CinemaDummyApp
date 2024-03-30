package com.example.cinemadummyapp.interaction.ticket_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cinemadummyapp.MainViewModel

@Composable
fun TicketDetailsScreen(
    mainViewModel: MainViewModel,
    ticketId: String,
    onBackClicked: () -> Unit = {},
    goToTickets: () -> Unit = {},
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Red)) {

    }
}