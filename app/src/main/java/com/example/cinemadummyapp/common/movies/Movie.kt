package com.example.cinemadummyapp.common.movies

import androidx.annotation.DrawableRes

data class Movie(
    val id: String,
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
    val director: String,
    val trailerUrl: String,
)