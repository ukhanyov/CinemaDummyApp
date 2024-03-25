package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.tickets.Ticket
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState

val movieDetailsScreenDefaultModifier = Modifier
    .fillMaxSize()
    .background(Color.Black)

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun MovieDetailsScreenPreview() {
//    CinemaDummyAppTheme {
//        MovieDetailsScreen(
//            modifier = movieDetailsScreenDefaultModifier,
//            movie = randomMovie,
//            onBackClicked = {},
//            onSessionSelected = { date, time -> },
//        )
//    }
//}

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movie: Movie,
    onBackClicked: () -> Unit,
    onSessionSelected: (String, String) -> Unit,
    cart: List<Ticket>,
) {
    var toolbarState by remember { mutableStateOf(ToolbarState.MovieDetails(cart = cart)) }
    Column(modifier = modifier) {
        AppToolbar(
            toolbarState = toolbarState,
            onTabSelected = { toolbarState = toolbarState.copy(selectedTabIndex = it) },
            onBackClicked = { onBackClicked() }
        )
        when {
            toolbarState.selectedTabIndex == 0 -> {
                MovieDetailsBookingScreen(
                    movieDetailsBookingScreenDefaultModifier,
                    movie,
                    onSessionSelected = { date, time -> onSessionSelected(date, time) })
            }

            toolbarState.selectedTabIndex == 1 -> {
                MovieDetailsShowcaseScreen(movieDetailsShowcaseScreenDefaultModifier, movie)
            }
        }
    }
}