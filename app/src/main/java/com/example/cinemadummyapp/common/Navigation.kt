package com.example.cinemadummyapp.common

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.cinemadummyapp.interaction.MovieDetails
import com.example.cinemadummyapp.interaction.MovieSeats

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateToMovieDetails(movieId: String) {
    this.navigateSingleTopTo("${MovieDetails.route}/$movieId")
}

fun NavHostController.navigateToMovieSeats(movieId: String) {
    this.navigateSingleTopTo("${MovieSeats.route}/$movieId")
}

fun NavHostController.onBackClicked() {
    this.popBackStack()
}