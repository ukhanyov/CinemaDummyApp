package com.example.cinemadummyapp.interaction

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface InteractionDestination {
    val icon: ImageVector
    val route: String
}

object Home : InteractionDestination {
    override val icon = Icons.Filled.Home
    override val route = "interaction/home"
}

object Tickets : InteractionDestination {
    override val icon = Icons.Filled.DateRange
    override val route = "interaction/tickets"
}

object Profile : InteractionDestination {
    override val icon = Icons.Filled.Person
    override val route = "interaction/profile"
}

object MovieDetails : InteractionDestination {
    override val icon = Icons.Filled.Star
    override val route = "interaction/movie_details"

    const val MOVIE_ID_ARF = "movie_id_arg"
    val routeWithArgs = "$route/{$MOVIE_ID_ARF}"

    val arguments = listOf(
        navArgument(MOVIE_ID_ARF) { type = NavType.StringType }
    )
}

object MovieSeats : InteractionDestination {
    override val icon = Icons.Filled.Star
    override val route = "interaction/movie_seats"

    const val MOVIE_ID_ARF = "movie_id_arg"
    const val DATE_ARG = "date_arg"
    const val TIME_ARG = "time_arg"
    val routeWithArgs = "$route/{$MOVIE_ID_ARF}/{$DATE_ARG}/{$TIME_ARG}"

    val arguments = listOf(
        navArgument(MOVIE_ID_ARF) { type = NavType.StringType }
    )
}

val interactionScreens = listOf(Home, /*Tickets,*/ Profile)