package com.example.cinemadummyapp.interaction

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemadummyapp.common.movies.allMovies
import com.example.cinemadummyapp.common.navigateToMovieDetails
import com.example.cinemadummyapp.common.navigateToMovieSeats
import com.example.cinemadummyapp.common.onBackClicked
import com.example.cinemadummyapp.interaction.home.HomeScreen
import com.example.cinemadummyapp.interaction.home.HomeState
import com.example.cinemadummyapp.interaction.movie_details.MovieDetailsScreen
import com.example.cinemadummyapp.interaction.movie_details.movieDetailsScreenDefaultModifier
import com.example.cinemadummyapp.interaction.profile.ProfileScreen
import com.example.cinemadummyapp.interaction.theater_seats.TheaterSeatsScreen
import com.example.cinemadummyapp.interaction.theater_seats.theaterSeatsScreenDefaultModifier
import com.example.cinemadummyapp.interaction.tickets.TicketsScreen

@Composable
fun InteractionNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    hideBottomNavigation: (Boolean) -> Unit
) {
    var homeState by remember { mutableStateOf(HomeState()) }
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
            HomeScreen(
                homeState = homeState,
                onTabSelected = { homeState = homeState.copy(selectedTabIndex = it) },
                onMovieSelected = { navController.navigateToMovieDetails(it.id) },
            )
            hideBottomNavigation(false)
        }
        composable(route = Tickets.route) {
            TicketsScreen()
            hideBottomNavigation(false)
        }
        composable(route = Profile.route) {
            ProfileScreen()
            hideBottomNavigation(false)
        }
        composable(
            route = MovieDetails.routeWithArgs,
            arguments = MovieDetails.arguments
        ) {
            val movieId = it.arguments?.getString(MovieDetails.movieIdArg)!!
            val movie = allMovies.first { it.id == movieId }
            MovieDetailsScreen(
                movie = movie,
                modifier = movieDetailsScreenDefaultModifier,
                onBackClicked = { navController.onBackClicked() },
                onSessionSelected = { navController.navigateToMovieSeats(movieId) }
            )
            hideBottomNavigation(true)
        }
        composable(
            route = MovieSeats.routeWithArgs,
            arguments = MovieSeats.arguments
        ) {
            val movieId = it.arguments?.getString(MovieSeats.movieIdArg)!!
            val movie = allMovies.first { it.id == movieId }
            TheaterSeatsScreen(
                movie = movie,
                modifier = theaterSeatsScreenDefaultModifier,
                onBackClicked = { navController.onBackClicked() },
            )
            hideBottomNavigation(true)
        }
    }
}