package com.example.cinemadummyapp.common

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.cinemadummyapp.interaction.MovieDetails
import com.example.cinemadummyapp.interaction.MovieSeats
import com.example.cinemadummyapp.interaction.TicketDetails

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = false
    }
    launchSingleTop = true
    restoreState = false
}

fun NavHostController.navigateToMovieDetails(movieId: String) {
    this.navigateSingleTopTo("${MovieDetails.route}/$movieId")
}

fun NavHostController.navigateToMovieSeats(movieId: String, date: String, time: String) {
    this.navigateSingleTopTo("${MovieSeats.route}/$movieId/$date/$time")
}

fun NavHostController.navigateToTicketDetails(id: String) {
    this.navigateSingleTopTo("${TicketDetails.route}/$id")
}

fun NavHostController.onBackClicked() {
    this.popBackStack()
}