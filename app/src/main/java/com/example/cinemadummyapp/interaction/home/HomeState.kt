package com.example.cinemadummyapp.interaction.home

import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.comingSoon
import com.example.cinemadummyapp.common.movies.onTheater
import com.example.cinemadummyapp.common.movies.randomMovie

data class HomeState(
    val movie: Movie = randomMovie,
    val posterAction: String = "Get your tickets now",
    val allTabs: List<String> = listOf("On Theater", "Coming Soon"),
    val selectedTabIndex: Int = 0,
    val onTheaterList: List<Movie> = onTheater,
    val comingSoonList: List<Movie> = comingSoon,
)