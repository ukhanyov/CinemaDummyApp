package com.example.cinemadummyapp.common.movies

import androidx.annotation.DrawableRes

data class Movie(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)