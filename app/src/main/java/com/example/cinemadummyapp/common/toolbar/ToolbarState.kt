package com.example.cinemadummyapp.common.toolbar

import com.example.cinemadummyapp.MainViewModel

sealed class ToolbarState {

    data class Home(
        val title: String = "Movies",
        val mainViewModel: MainViewModel,
    ) : ToolbarState()

    data class MovieDetails(
        val title: String = "Movie Details",
        val selectedTabIndex: Int = 0,
        val allTabs: List<String> = listOf("Booking", "Details"),
        val mainViewModel: MainViewModel,
    ) : ToolbarState()

    data class TheaterSeats(
        val title: String = "Choose Seats",
        val mainViewModel: MainViewModel,
    ) : ToolbarState()

    data class Profile(
        val title: String = "Account",
        val mainViewModel: MainViewModel,
    ) : ToolbarState()

    data class Payment(
        val title: String = "Check Out",
    ) : ToolbarState()

}