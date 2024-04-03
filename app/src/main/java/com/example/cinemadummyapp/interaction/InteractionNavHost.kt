package com.example.cinemadummyapp.interaction

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.common.movies.allMovies
import com.example.cinemadummyapp.common.navigateSingleTopTo
import com.example.cinemadummyapp.common.navigateToMovieDetails
import com.example.cinemadummyapp.common.navigateToMovieSeats
import com.example.cinemadummyapp.common.onBackClicked
import com.example.cinemadummyapp.common.tickets.Ticket
import com.example.cinemadummyapp.interaction.home.HomeScreen
import com.example.cinemadummyapp.interaction.home.HomeState
import com.example.cinemadummyapp.interaction.movie_details.MovieDetailsScreen
import com.example.cinemadummyapp.interaction.movie_details.movieDetailsScreenDefaultModifier
import com.example.cinemadummyapp.interaction.payment.PaymentScreen
import com.example.cinemadummyapp.interaction.profile.ProfileScreen
import com.example.cinemadummyapp.interaction.theater_seats.TheaterSeatsScreen
import com.example.cinemadummyapp.interaction.theater_seats.theaterSeatsScreenDefaultModifier
import com.example.cinemadummyapp.interaction.ticket_details.TicketDetailsScreen
import com.example.cinemadummyapp.interaction.tickets.TicketsScreen

@Composable
fun InteractionNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    hideBottomNavigation: (Boolean) -> Unit,
    onProfileDeleted: () -> Unit,
    onProfileChange: () -> Unit,
    addToCart: (List<Ticket>) -> Unit = {},
    mainViewModel: MainViewModel,
    onCartClicked: () -> Unit,
) {
    var homeState by remember { mutableStateOf(HomeState()) }
    NavHost(
        navController = navController,
        startDestination = Home.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(350)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(350)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(350)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(350)
            )
        },
        modifier = modifier,
    ) {
        composable(route = Home.route) {
            HomeScreen(
                homeState = homeState,
                mainViewModel = mainViewModel,
                onTabSelected = { homeState = homeState.copy(selectedTabIndex = it) },
                onMovieSelected = { movie -> navController.navigateToMovieDetails(movie.id) },
                onCartClicked = { onCartClicked() },
            )
            hideBottomNavigation(false)
        }
        composable(route = Tickets.route) {
            TicketsScreen(
                mainViewModel = mainViewModel,
                onBackClicked = { navController.onBackClicked() },
                onCartClicked = { onCartClicked() },
                onTicketClicked = { ticket -> /*navController.navigateToTicketDetails(ticket.id)*/ }
            )
            hideBottomNavigation(false)
        }
        composable(route = Profile.route) {
            ProfileScreen(
                mainViewModel = mainViewModel,
                onBackClicked = { navController.onBackClicked() },
                onProfileDeleted = { onProfileDeleted() },
                onProfileChange = { onProfileChange() },
                onCartClicked = { onCartClicked() },
            )
            hideBottomNavigation(false)
        }
        composable(route = MovieDetails.routeWithArgs, arguments = MovieDetails.arguments) {
            val movieId = it.arguments?.getString(MovieDetails.MOVIE_ID_ARF)!!
            val movie = allMovies.first { it.id == movieId }
            MovieDetailsScreen(
                movie = movie,
                modifier = movieDetailsScreenDefaultModifier,
                onBackClicked = { navController.onBackClicked() },
                onSessionSelected = { date, time ->
                    navController.navigateToMovieSeats(
                        movieId,
                        date,
                        time
                    )
                },
                mainViewModel = mainViewModel,
                onCartClicked = { onCartClicked() },
            )
            hideBottomNavigation(true)
        }
        composable(route = MovieSeats.routeWithArgs, arguments = MovieSeats.arguments) {
            val movieId = it.arguments?.getString(MovieSeats.MOVIE_ID_ARF)!!
            val date = it.arguments?.getString(MovieSeats.DATE_ARG)!!
            val time = it.arguments?.getString(MovieSeats.TIME_ARG)!!
            val movie = allMovies.first { it.id == movieId }
            TheaterSeatsScreen(
                modifier = theaterSeatsScreenDefaultModifier,
                movie = movie,
                dateText = date,
                timeText = time,
                mainViewModel = mainViewModel,
                onBackClicked = { navController.onBackClicked() },
                addToCart = { tickets -> addToCart(tickets) },
                onCartClicked = { onCartClicked() },
            )
            hideBottomNavigation(true)
        }
        composable(route = Payment.route) {
            PaymentScreen(
                mainViewModel,
                onBackClicked = { navController.onBackClicked() },
                goToTickets = { navController.navigateSingleTopTo(Tickets.route) })
            hideBottomNavigation(true)
        }
        composable(route = TicketDetails.routeWithArgs, arguments = TicketDetails.arguments) {
            TicketDetailsScreen(
                mainViewModel = mainViewModel,
                ticketId = it.arguments?.getString(TicketDetails.ID_ARF)!!,
                onBackClicked = { navController.onBackClicked() },
                goToTickets = { navController.navigateSingleTopTo(Tickets.route) })
            hideBottomNavigation(true)
        }
    }
}