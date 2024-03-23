package com.example.cinemadummyapp.interaction.theater_seats

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
import com.example.cinemadummyapp.common.tickets.TicketState
import com.example.cinemadummyapp.common.tickets.makeTicketsGrid
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

val theaterSeatsScreenDefaultModifier = Modifier
    .fillMaxSize()

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun MovieDetailsShowcaseScreenPreview() {
    CinemaDummyAppTheme {
        TheaterSeatsScreen(
            modifier = theaterSeatsScreenDefaultModifier,
            movie = randomMovie
        )
    }
}

@Composable
fun TheaterSeatsScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    movie: Movie,
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
        isButtonEnabled = ticketsGrid.any { it.ticketState == TicketState.Selected }
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
                        Image(
                            modifier = Modifier.size(36.dp),
                            imageVector = ImageVector.vectorResource(id = R.drawable.seat),
                            contentDescription = null
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
//        LazyVerticalGrid(
//            modifier = Modifier.sizeIn(maxWidth = (19.dp * 6 + (4.dp * 6))),
//            verticalArrangement = Arrangement.spacedBy(4.dp), // Adjust spacing as needed
//            horizontalArrangement = Arrangement.Center,
//            columns = GridCells.Fixed(6)
//        ) {
//            items(ticketsGrid, key = { it.id }) {
//                Image(
//                    modifier = Modifier,
//                    imageVector = ImageVector.vectorResource(id = R.drawable.seat),
//                    contentDescription = null
//                )
//            }
//        }
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