package com.example.cinemadummyapp.common

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.cinemadummyapp.interaction.MovieDetails

fun NavHostController.navigateSingleTopTo(route: String) = this.navigate(route) {
    popUpTo(
        this@navigateSingleTopTo.graph.findStartDestination().id
    ) {
        saveState = true
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateToMovieDetails(movie: String) {
    this.navigateSingleTopTo("${MovieDetails.route}/$movie")
}