package com.example.cinemadummyapp.common.toolbar

sealed class ToolbarState {

    data class Home(
        val title: String = "Movies"
    ) : ToolbarState()

    data class MovieDetails(
        val title: String = "Movie Details",
        val selectedTabIndex: Int = 0,
        val allTabs: List<String> = listOf("Booking", "Details"),
    ) : ToolbarState()

    data class TheaterSeats(
        val title: String = "Choose Seats",
    ) : ToolbarState()

}