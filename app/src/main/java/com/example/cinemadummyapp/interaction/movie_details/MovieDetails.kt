package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MovieDetailsScreenPreview() {
    CinemaDummyAppTheme {
        MovieDetailsScreen()
    }
}


@Composable
fun MovieDetailsScreen(
    movie: Movie = randomMovie,
    modifier: Modifier = Modifier
) {

}