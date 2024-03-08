package com.example.cinemadummyapp.interaction.home

import androidx.annotation.DrawableRes
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.ToolbarState
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.onTheater

data class HomeState(
    val toolbarState: ToolbarState = ToolbarState.Home(),
    @DrawableRes val poster: Int = R.drawable.the_batman_poster,
    val posterFilmName: String = "The Batman",
    val posterAction: String = "Get your tickets now",
    val allTabs: List<String> = listOf("On Theater", "Coming Soon"),
    val selectedTabIndex: Int = 0,
    val onTheaterList: List<Movie> = onTheater,
)