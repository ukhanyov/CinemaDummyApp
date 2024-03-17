package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme
import java.time.format.DateTimeFormatter

val movieDetailsBookingScreenDefaultModifier = Modifier
    .fillMaxSize()

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MovieDetailsBookingScreenPreview() {
    CinemaDummyAppTheme {
        MovieDetailsBookingScreen(modifier = movieDetailsBookingScreenDefaultModifier)
    }
}

@Composable
fun MovieDetailsBookingScreen(
    modifier: Modifier = Modifier,
    movie: Movie = randomMovie
) {
    var bookingData by remember {
        mutableStateOf(movie.generateBookingData())
    }
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = movie.description,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.weight(0.2f),
                text = "Sessions this week",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.8f)
            ) {
                items(bookingData.schedule, key = { it.toEpochSecond() }) {
                    Column(
                        modifier = Modifier
                            .padding(4.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = it.format(DateTimeFormatter.ofPattern("E")).capitalize() +
                                    "\n" +
                                    it.format(DateTimeFormatter.ofPattern("M/d")).capitalize(),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = "${it.hour}:${it.minute}")
                    }
                }
            }
        }
    }
}
