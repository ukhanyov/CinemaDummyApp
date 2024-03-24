package com.example.cinemadummyapp.interaction.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
        MovieListScreen(onTheater)
    }
}


@Composable
fun MovieListScreen(
    list: List<Movie>,
    onMovieSelected: (Movie) -> Unit = {},
) {
    val screenWidthDp = with(LocalDensity.current) {
        LocalConfiguration.current.screenWidthDp.dp.toPx()
    }

    val itemPadding = 16.dp
    val itemWidthDp = 120.dp + itemPadding * 2
    val itemWidthPx = with(LocalDensity.current) { itemWidthDp.toPx() }
    val columnCount = (screenWidthDp / itemWidthPx).toInt()

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(list, key = { it.id }) {
            Box(
                modifier = Modifier
                    .padding(itemPadding)
                    .size(itemWidthDp)
                    .aspectRatio(1f)
                    .clickable { onMovieSelected(it) }
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
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
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