package com.example.cinemadummyapp.interaction.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.common.movies.Movie
import com.example.cinemadummyapp.common.movies.onTheater
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun OnTheaterScreenPreview() {
    CinemaDummyAppTheme {
        OnTheaterScreen(onTheater)
    }
}


@Composable
fun OnTheaterScreen(
    list: List<Movie>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(list, key = { it.id }) {
            Box(
                modifier = Modifier
                    .fillMaxSize() // Make Box take maximum space within parent
                    .aspectRatio(1f) // Enforce a 1:1 (square) aspect ratio
//                    .weight(1f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(it.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(1f)) {}
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                Brush.verticalGradient(
                                    0F to Color.Transparent,
                                    .5F to Color.Black.copy(alpha = 0.5f),
                                    1F to Color.Black.copy(alpha = 1f)
                                )
                            ),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            text = it.title,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}