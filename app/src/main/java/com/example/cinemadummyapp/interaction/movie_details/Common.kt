package com.example.cinemadummyapp.interaction.movie_details

import com.example.cinemadummyapp.common.movies.Movie
import java.time.ZonedDateTime

data class Booking(
    val schedule: List<ZonedDateTime>,
    val movie: Movie,
    val selectedDate: ZonedDateTime?,
)

fun Movie.generateBookingData() = Booking(
    schedule = getNewSchedule(),
    movie = this,
    selectedDate = null
)

private fun getNewSchedule(): List<ZonedDateTime> {
    val currentDate = ZonedDateTime.now()
    val dates = mutableSetOf<ZonedDateTime>()
    dates.add(currentDate)
    for (i in 1..7) {
        currentDate.plusDays(i.toLong()).let {
            dates.add(it)
        }
        currentDate.minusDays(i.toLong()).let {
            dates.add(it)
        }
    }

    return dates.sorted()
}