package com.example.cinemadummyapp.interaction.movie_details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
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
    val screenWidthDp = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }
    var bookingData by remember { mutableStateOf(movie.generateBookingData()) }
    if (bookingData.selectedDate == null) {
        bookingData = bookingData.copy(
            selectedDate = bookingData.schedule.firstOrNull { it.isToday() }
        )
    }
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(movie.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
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
                    .padding(top = 32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier,
                        text = "Available Sessions:",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    val datesListState = rememberLazyListState()
                    var withScrollToSelectedDate by remember { mutableStateOf(true) }
                    LazyRow(
                        modifier = Modifier.padding(top = 16.dp),
                        state = datesListState
                    ) {
                        items(bookingData.schedule, key = { it.toEpochSecond() }) {
                            val textColor =
                                if (bookingData.selectedDate == it) Color.Black else Color.White
                            val cardBorderColor =
                                if (bookingData.selectedDate == it) Color.White else Color(
                                    0xFF242424
                                )
                            val cardFillColor =
                                if (bookingData.selectedDate == it) Color.White else Color(
                                    0xFF181818
                                )
                            Card(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .clickable {
                                        bookingData = bookingData.copy(
                                            selectedDate = it,
                                            selectedTime = null,
                                        )
                                    },
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
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val text = when {
                                        it.isToday() -> "Today"
                                        it.isTomorrow() -> "Tomorrow"
                                        it.isYesterday() -> "Yesterday"
                                        else -> it.format(DateTimeFormatter.ofPattern("E"))
                                            .replaceFirstChar {
                                                if (it.isLowerCase()) it.titlecase(
                                                    Locale.getDefault()
                                                ) else it.toString()
                                            }
                                    }
                                    Text(
                                        text = text,
                                        color = textColor,
                                        fontSize = 24.sp,
                                    )
                                    Text(
                                        text = it.format(DateTimeFormatter.ofPattern("M/d"))
                                            .replaceFirstChar {
                                                if (it.isLowerCase()) it.titlecase(
                                                    Locale.getDefault()
                                                ) else it.toString()
                                            },
                                        color = textColor,
                                        fontSize = 20.sp,
                                    )
                                }
                            }
                        }
                    }
                    if (withScrollToSelectedDate && bookingData.selectedDate != null) {
                        withScrollToSelectedDate = false
                        LaunchedEffect(true) {
                            datesListState.animateScrollToItem(
                                bookingData.schedule.indexOf(bookingData.selectedDate),
                                -(screenWidthDp / 2).toInt()
                            )
                        }
                    }
                    if (bookingData.selectedDate != null) {
                        LazyRow(
                            modifier = Modifier
                                .padding(top = 16.dp)
                        ) {
                            items(bookingData.timeSlots, key = { it }) {
                                val textColor =
                                    if (bookingData.selectedTime == it) Color.Black else Color.White
                                val cardBorderColor =
                                    if (bookingData.selectedTime == it) Color.White else Color(
                                        0xFF242424
                                    )
                                val cardFillColor =
                                    if (bookingData.selectedTime == it) Color.White else Color(
                                        0xFF181818
                                    )
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .clickable {
                                            bookingData = bookingData.copy(selectedTime = it)
                                        },
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
                                        modifier = Modifier.padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = it,
                                            color = textColor,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                if (bookingData.selectedDate != null && bookingData.selectedTime != null) Button(
                    modifier = Modifier
                        .padding(horizontal = 48.dp, vertical = 16.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { },
                ) {
                    Text(
                        text = "Reserve",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}
