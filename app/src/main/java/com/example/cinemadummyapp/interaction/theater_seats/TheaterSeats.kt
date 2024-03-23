package com.example.cinemadummyapp.interaction.theater_seats

import android.app.Activity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.tickets.TicketState.*
import com.example.cinemadummyapp.common.tickets.makeTicketsGrid
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.ticketsAvailable
import com.example.cinemadummyapp.ui.theme.ticketsReserved
import com.example.cinemadummyapp.ui.theme.ticketsSelected

val theaterSeatsScreenDefaultModifier = Modifier
    .fillMaxSize()

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun MovieDetailsShowcaseScreenPreview() {
//    CinemaDummyAppTheme {
//        TheaterSeatsScreen(
//            modifier = theaterSeatsScreenDefaultModifier,
//            movie = randomMovie,
//            dateText = "Mon 22/11",
//            timeText = "19:00"
//        )
//    }
//}

@Composable
fun TheaterSeatsScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    movie: Movie,
    dateText: String,
    timeText: String,
) {

    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.White.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = true

    var isButtonEnabled by remember { mutableStateOf(false) }
    var ticketsGrid by remember { mutableStateOf(makeTicketsGrid(movie)) }
    LaunchedEffect(ticketsGrid) {
        isButtonEnabled = ticketsGrid.any { it.ticketState == Selected }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(
            toolbarState = ToolbarState.TheaterSeats(),
            onBackClicked = { onBackClicked() }
        )
        Column {
            ticketsGrid.groupBy { it.row }.forEach { (row, tickets) ->
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(top = 6.dp),
                        text = "$row",
                        fontSize = 24.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                    tickets.forEach { ticket ->
                        val color = when (ticket.ticketState) {
                            Available -> ticketsAvailable
                            Selected -> ticketsSelected
                            Reserved -> ticketsReserved
                        }
                        Image(
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    when (ticket.ticketState) {
                                        Available -> {
                                            val index = ticketsGrid.indexOf(ticket)
                                            ticketsGrid = ticketsGrid
                                                .toMutableList()
                                                .apply {
                                                    this[index] =
                                                        ticket.copy(ticketState = Selected)
                                                }
                                        }

                                        Selected -> {
                                            val index = ticketsGrid.indexOf(ticket)
                                            ticketsGrid = ticketsGrid
                                                .toMutableList()
                                                .apply {
                                                    this[index] =
                                                        ticket.copy(ticketState = Available)
                                                }
                                        }

                                        Reserved -> {}
                                    }
                                },
                            imageVector = ImageVector.vectorResource(id = R.drawable.seat),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color)
                        )
                    }
                }
            }
            Row {
                Spacer(modifier = Modifier.size(36.dp))
                ticketsGrid.map { it.seat }.toSet().forEach {
                    Text(
                        modifier = Modifier
                            .size(36.dp),
                        text = it,
                        fontSize = 24.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(modifier = Modifier.size(25.dp)) {
                    drawCircle(color = ticketsAvailable)
                }
                Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                Text(text = "Available: ${ticketsGrid.count { it.ticketState == Available }}")
            }
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(modifier = Modifier.size(25.dp)) {
                    drawCircle(color = ticketsReserved)
                }
                Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                Text(text = "Reserved: ${ticketsGrid.count { it.ticketState == Reserved }}")
            }
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Canvas(modifier = Modifier.size(25.dp)) {
                    drawCircle(color = ticketsSelected)
                }
                Spacer(modifier = Modifier.size(width = 8.dp, height = 0.dp))
                Text(text = "Selected: ${ticketsGrid.count { it.ticketState == Selected }}")
            }
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Movie: ${movie.title}",
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Date: ${dateText}",
            )
            Text(
                modifier = Modifier.padding(vertical = 4.dp),
                text = "Session: ${timeText}",
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
            enabled = isButtonEnabled,
            onClick = { /*todo*/ },
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                text = "Continue",
                color = if (isButtonEnabled) Color.White else Color.DarkGray,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}