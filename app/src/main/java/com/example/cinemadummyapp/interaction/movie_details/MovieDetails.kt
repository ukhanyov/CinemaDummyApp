package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
        MovieDetailsScreen(modifier = movieDetailsScreenDefaultModifier)
    }
}

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movie: Movie = randomMovie
) {
    var toolbarState by remember { mutableStateOf(ToolbarState.MovieDetails()) }
    Column(modifier = modifier) {
        AppToolbar(toolbarState) {
            toolbarState = toolbarState.copy(selectedTabIndex = it)
        }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(movie.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    0F to Color.Transparent,
                    .5F to Color.Black.copy(alpha = 0.5F),
                    1F to Color.Black.copy(alpha = 0.8F)
                )
            )

    ) {
        when {
            toolbarState.selectedTabIndex == 0 -> {
                MovieDetailsBookingScreen(movieDetailsBookingScreenDefaultModifier, movie)
            }

            toolbarState.selectedTabIndex == 1 -> {

            }
        }
    }
}