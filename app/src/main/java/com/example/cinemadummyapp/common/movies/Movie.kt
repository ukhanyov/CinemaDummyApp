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

fun Movie.toArgData(): String {
    return "${this.id}$deliminator${this.image}$deliminator${this.title}$deliminator${this.description}"
}

fun String.fromArgDataToMovie(): Movie {
    val split = this.split(deliminator)
    return Movie(
        id = split[0],
        image = split[1].toInt(),
        title = split[2],
        description = split[3],
        director = split[4],
        trailerUrl = split[5],
    )
}

private const val deliminator = "@____|____@____&_____@"