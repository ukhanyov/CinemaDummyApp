package com.example.cinemadummyapp.common.tickets

import com.example.cinemadummyapp.common.movies.Movie
import kotlin.random.Random

fun makeTicketsGrid(movie: Movie, numberOfRows: Int = 8): List<Ticket> {
    val list = mutableListOf<Ticket>()

    for (i in 1..numberOfRows) {
        list.addAll(makeRow(movie, i))
    }

    return list
}

private fun makeRow(
    movie: Movie,
    row: Int,
): List<Ticket> {
    return listOf(
        Ticket(
            date = "",
            time = "",
            movie = movie,
            row = row,
            seat = "A",
            ticketState = listOf(TicketState.Available, TicketState.Reserved).random(),
            price = getPrice(),
        ),
        Ticket(
            date = "",
            time = "",
            movie = movie,
            row = row,
            seat = "B",
            ticketState = listOf(TicketState.Available, TicketState.Reserved).random(),
            price = getPrice(),
        ),
        Ticket(
            date = "",
            time = "",
            movie = movie,
            row = row,
            seat = "C",
            ticketState = listOf(TicketState.Available, TicketState.Reserved).random(),
            price = getPrice(),
        ),
        Ticket(
            date = "",
            time = "",
            movie = movie,
            row = row,
            seat = "D",
            ticketState = listOf(TicketState.Available, TicketState.Reserved).random(),
            price = getPrice(),
        ),
        Ticket(
            date = "",
            time = "",
            movie = movie,
            row = row,
            seat = "E",
            ticketState = listOf(TicketState.Available, TicketState.Reserved).random(),
            price = getPrice(),
        ),
        Ticket(
            date = "",
            time = "",
            movie = movie,
            row = row,
            seat = "F",
            ticketState = listOf(TicketState.Available, TicketState.Reserved).random(),
            price = getPrice(),
        ),
    )
}

private fun getPrice() = listOf(5,7,10,12,15).random()