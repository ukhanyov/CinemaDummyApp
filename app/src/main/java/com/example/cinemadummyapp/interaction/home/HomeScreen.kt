package com.example.cinemadummyapp.interaction.home

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.common.Toolbar
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

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
    homeState: HomeState = HomeState(),
    onTabSelected: (Int) -> Unit = {}
) {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.Black.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = false

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
                Column {
                    TabRow(
                        modifier = Modifier,
                        selectedTabIndex = homeState.selectedTabIndex,
                        containerColor = Color.Black,
                        divider = {}
                    ) {
                        homeState.allTabs.forEachIndexed { index, title ->
                            Tab(
                                modifier = Modifier.background(Color.Black),
                                text = {
                                    Text(
                                        text = title,
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )
                                },
                                selected = homeState.selectedTabIndex == index,
                                onClick = { onTabSelected(index) },
                            )
                        }
                    }
                    when {
                        homeState.allTabs[homeState.selectedTabIndex] == "On Theater" -> {
                            MovieListScreen(homeState.onTheaterList)
                        }

                        homeState.allTabs[homeState.selectedTabIndex] == "Coming Soon" -> {
                            MovieListScreen(homeState.comingSoonList)
                        }
                    }
                }
            }
        }
    }
}