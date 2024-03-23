package com.example.cinemadummyapp.common.tickets

import com.example.cinemadummyapp.common.movies.Movie

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
            movie = movie,
            row = row,
            seat = "A",
            ticketState = TicketState.entries.random()
        ),
        Ticket(
            movie = movie,
            row = row,
            seat = "B",
            ticketState = TicketState.entries.random()
        ),
        Ticket(
            movie = movie,
            row = row,
            seat = "C",
            ticketState = TicketState.entries.random()
        ),
        Ticket(
            movie = movie,
            row = row,
            seat = "D",
            ticketState = TicketState.entries.random()
        ),
        Ticket(
            movie = movie,
            row = row,
            seat = "E",
            ticketState = TicketState.entries.random()
        ),
        Ticket(
            movie = movie,
            row = row,
            seat = "F",
            ticketState = TicketState.entries.random()
        ),
    )
}