package com.example.cinemadummyapp.interaction

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemadummyapp.common.movies.fromArgDataToMovie
import com.example.cinemadummyapp.common.movies.toArgData
import com.example.cinemadummyapp.common.navigateToMovieDetails
import com.example.cinemadummyapp.interaction.home.HomeScreen
import com.example.cinemadummyapp.interaction.home.HomeState
import com.example.cinemadummyapp.interaction.movie_details.MovieDetailsScreen
import com.example.cinemadummyapp.interaction.movie_details.movieDetailsScreenDefaultModifier
import com.example.cinemadummyapp.interaction.profile.ProfileScreen
import com.example.cinemadummyapp.interaction.tickets.TicketsScreen

@Composable
fun InteractionNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    var homeState by remember {
        mutableStateOf(HomeState())
    }
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                homeState = homeState,
                onTabSelected = {
                    homeState = homeState.copy(selectedTabIndex = it)
                },
                onMovieSelected = {
                    navController.navigateToMovieDetails(it.toArgData())
                },
            )
        }
        composable(route = Tickets.route) {
            TicketsScreen()
        }
        composable(route = Profile.route) {
            ProfileScreen()
        }
        composable(
            route = MovieDetails.routeWithArgs,
            arguments = MovieDetails.arguments
        ) {
            val movie = it.arguments?.getString(MovieDetails.movieArg)?.fromArgDataToMovie()!!
            MovieDetailsScreen(
                movie = movie,
                modifier = movieDetailsScreenDefaultModifier
            )
        }
    }
}