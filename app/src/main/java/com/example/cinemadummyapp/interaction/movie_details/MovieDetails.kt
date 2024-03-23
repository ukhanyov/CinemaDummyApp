package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

val movieDetailsScreenDefaultModifier = Modifier
    .fillMaxSize()
    .background(Color.Black)

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MovieDetailsScreenPreview() {
    CinemaDummyAppTheme {
        MovieDetailsScreen(
            modifier = movieDetailsScreenDefaultModifier,
            movie = randomMovie,
            onBackClicked = {},
            onSessionSelected = {},
        )
    }
}

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movie: Movie,
    onBackClicked: () -> Unit,
    onSessionSelected: () -> Unit,
) {
    var toolbarState by remember { mutableStateOf(ToolbarState.MovieDetails()) }
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
                    onSessionSelected = { onSessionSelected() })
            }

            toolbarState.selectedTabIndex == 1 -> {
                MovieDetailsShowcaseScreen(movieDetailsShowcaseScreenDefaultModifier, movie)
            }
        }
    }
}