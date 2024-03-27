package com.example.cinemadummyapp.common.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun HomeScreenPreview() {
//    CinemaDummyAppTheme {
//        AppToolbar()
//    }
//}

@Composable
fun AppToolbar(
    toolbarState: ToolbarState,
    onTabSelected: (Int) -> Unit = {},
    onBackClicked: () -> Unit = {},
    onCartClicked: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        when (toolbarState) {
            is ToolbarState.Home -> HomeToolbar(
                state = toolbarState,
                onCartClicked = { onCartClicked() },
            )

            is ToolbarState.MovieDetails -> MovieDetailsToolbar(
                state = toolbarState,
                onTabSelected = { onTabSelected(it) },
                onBackClicked = { onBackClicked() },
                onCartClicked = { onCartClicked() },
            )

            is ToolbarState.TheaterSeats -> TheaterSeatsToolbar(
                state = toolbarState,
                onBackClicked = { onBackClicked() },
                onCartClicked = { onCartClicked() },
            )

            is ToolbarState.Profile -> ProfileToolbar(
                state = toolbarState,
                onBackClicked = { onBackClicked() },
                onCartClicked = { onCartClicked() },
            )

            is ToolbarState.Payment -> PaymentToolbar(
                state = toolbarState,
                onBackClicked = { onBackClicked() },
            )
        }
    }
}