package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(5.dp),
            painter = painterResource(movie.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(0.35f))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.65f)
                    .scrollable(rememberScrollState(), Orientation.Vertical),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Storyline",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.size(height = 16.dp, width = 0.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = movie.description,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.size(height = 24.dp, width = 0.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Director:",
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 20.sp
                    )
                    Spacer(modifier = Modifier.size(height = 0.dp, width = 8.dp))
                    Text(
                        text = movie.director,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}