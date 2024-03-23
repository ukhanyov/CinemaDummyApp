package com.example.cinemadummyapp.interaction.theater_seats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.randomMovie
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
        TheaterSeatsScreen(modifier = theaterSeatsScreenDefaultModifier)
    }
}

@Composable
fun TheaterSeatsScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    movie: Movie = randomMovie,
) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(
            toolbarState = ToolbarState.TheaterSeats(),
            onBackClicked = { onBackClicked() }
        )
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