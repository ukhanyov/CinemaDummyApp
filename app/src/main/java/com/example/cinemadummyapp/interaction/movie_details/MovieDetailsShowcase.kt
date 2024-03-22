package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.*
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

val movieDetailsShowcaseScreenDefaultModifier = Modifier
    .fillMaxSize()

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MovieDetailsShowcaseScreenPreview() {
    CinemaDummyAppTheme {
        MovieDetailsShowcaseScreen(modifier = movieDetailsShowcaseScreenDefaultModifier)
    }
}

@Composable
fun MovieDetailsShowcaseScreen(
    modifier: Modifier = Modifier,
    movie: Movie = randomMovie
) {

}