package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun HomeScreenPreview() {
    CinemaDummyAppTheme {
        AppToolbar()
    }
}

@Composable
fun AppToolbar(
    toolbarState: ToolbarState = ToolbarState.MovieDetails(),
    onTabSelected: (Int) -> Unit = {},
    onBackClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        when (toolbarState) {
            is ToolbarState.Home -> HomeToolbar(toolbarState)
            is ToolbarState.MovieDetails -> MovieDetailsToolbar(
                state = toolbarState,
                onTabSelected = { onTabSelected(it) },
                onBackClicked = { onBackClicked() },
            )

            is ToolbarState.TheaterSeats -> TheaterSeatsToolbar(
                onBackClicked = { onBackClicked() },
            )

            is ToolbarState.Profile -> ProfileToolbar(
                onBackClicked = { onBackClicked() },
            )
        }
    }
}