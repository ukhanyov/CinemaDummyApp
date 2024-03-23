package com.example.cinemadummyapp.common.tickets

import com.example.cinemadummyapp.common.movies.Movie

data class Ticket(
    val movie: Movie,
    val row: Int,
    val seat: String,
    val ticketState: TicketState
)