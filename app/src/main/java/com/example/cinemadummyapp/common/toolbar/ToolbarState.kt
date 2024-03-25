package com.example.cinemadummyapp.common.toolbar

import com.example.cinemadummyapp.common.tickets.Ticket

sealed class ToolbarState {

    data class Home(
        val title: String = "Movies",
        val cart: List<Ticket>,
    ) : ToolbarState()

    data class MovieDetails(
        val title: String = "Movie Details",
        val selectedTabIndex: Int = 0,
        val allTabs: List<String> = listOf("Booking", "Details"),
        val cart: List<Ticket>,
    ) : ToolbarState()

    data class TheaterSeats(
        val title: String = "Choose Seats",
        val cart: List<Ticket>,
    ) : ToolbarState()

    data class Profile(
        val title: String = "Account",
        val cart: List<Ticket>,
    ) : ToolbarState()

}