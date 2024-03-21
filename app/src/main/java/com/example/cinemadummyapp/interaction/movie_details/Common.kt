package com.example.cinemadummyapp.interaction.movie_details

import com.example.cinemadummyapp.common.movies.Movie
import java.time.ZonedDateTime

data class Booking(
    val schedule: List<ZonedDateTime>,
    val timeSlots: List<String>,
    val movie: Movie,
    val selectedDate: ZonedDateTime?,
    val selectedTime: String?,
)

fun Movie.generateBookingData() = Booking(
    schedule = getNewSchedule(),
    movie = this,
    timeSlots = getTimeSlots(),
    selectedDate = null,
    selectedTime = null,
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

private fun getTimeSlots(): List<String> {
    val listOfTimes = listOf(
        "09:00",
        "09:30",
        "10:00",
        "10:30",
        "11:00",
        "11:30",
        "12:00",
        "12:30",
        "13:00",
        "13:30",
        "14:00",
        "14:30",
        "15:00",
        "15:30",
        "16:00",
        "16:30",
        "17:00",
        "17:30",
        "18:00",
        "18:30",
        "19:00",
        "19:30",
        "20:00",
        "20:30",
        "21:00",
        "21:30",
        "22:00",
        "22:30",
    )
    val returnList = mutableSetOf<String>()
    for (i in 0..10) {
        returnList.add(listOfTimes.random())
    }
    return returnList.toList().sorted()
}