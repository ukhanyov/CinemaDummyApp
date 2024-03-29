package com.example.cinemadummyapp.common.tickets

import com.example.cinemadummyapp.common.movies.Movie
import java.util.UUID

data class Ticket(
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val time: String,
    val movie: Movie,
    val row: Int,
    val seat: String,
    val ticketState: TicketState,
    val price: Int,
)