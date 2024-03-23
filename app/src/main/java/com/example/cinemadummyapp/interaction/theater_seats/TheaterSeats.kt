package com.example.cinemadummyapp.interaction.theater_seats

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

val theaterSeatsScreenDefaultModifier = Modifier

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MovieDetailsShowcaseScreenPreview() {
    CinemaDummyAppTheme {
        TheaterSeatsScreen(modifier = theaterSeatsScreenDefaultModifier)
    }
}

@Composable
fun TheaterSeatsScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    movie: Movie = randomMovie,
) {
    Column(modifier = modifier) {
        AppToolbar(
            toolbarState = ToolbarState.TheaterSeats(),
            onBackClicked = { onBackClicked() }
        )
    }
}