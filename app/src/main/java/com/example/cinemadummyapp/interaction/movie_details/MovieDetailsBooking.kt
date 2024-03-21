package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import java.util.Locale

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
                .weight(1f),
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
                    val textColor = Color.White
                    val cardBorderColor = Color(0xFF242424)
                    val cardFillColor = Color(0xFF181818)
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 4.dp),
                        colors = CardColors(
                            containerColor = cardFillColor,
                            contentColor = cardFillColor,
                            disabledContainerColor = cardFillColor,
                            disabledContentColor = cardFillColor,
                        ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = cardBorderColor,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = it.format(DateTimeFormatter.ofPattern("E"))
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                                color = textColor,
                            )
                            Text(
                                text = it.format(DateTimeFormatter.ofPattern("M/d"))
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                                color = textColor,
                            )
                        }
                    }
                }
            }
        }
    }
}
