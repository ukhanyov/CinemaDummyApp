package com.example.cinemadummyapp.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.Toolbar
import com.example.cinemadummyapp.common.ToolbarState
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

data class HomeState(
    val toolbarState: ToolbarState = ToolbarState.Home(),
    @DrawableRes val poster: Int = R.drawable.the_batman_poster,
    val posterFilmName: String = "The Batman",
    val posterAction: String = "Get your tickets now",
)

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    CinemaDummyAppTheme {
        HomeScreen()
    }
}


@Composable
fun HomeScreen(
    homeState: HomeState = HomeState()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar(homeState.toolbarState)
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(homeState.poster),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                0F to Color.Transparent,
                                .5F to Color.Black.copy(alpha = 0.5F),
                                1F to Color.Black.copy(alpha = 0.8F)
                            )
                        )
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp, top = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = homeState.posterFilmName,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                    Text(
                        text = homeState.posterAction,
                        color = Color.White,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Color.Black)
            ) {

            }
        }
    }
}