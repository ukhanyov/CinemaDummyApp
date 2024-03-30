package com.example.cinemadummyapp.interaction.tickets

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.common.TicketComp
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import kotlinx.coroutines.delay

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun TicketsScreenPreview() {
//    CinemaDummyAppTheme {
//        TicketsScreen()
//    }
//}


@Composable
fun TicketsScreen(
    mainViewModel: MainViewModel,
    onBackClicked: () -> Unit = {},
    onCartClicked: () -> Unit = {},
) {

    val localView = LocalView.current
    val activity = LocalView.current.context as Activity

    LaunchedEffect(key1 = true) {
        delay(150)
        activity.window.statusBarColor = Color.White.toArgb()
        WindowCompat.getInsetsController(activity.window, localView)
            .isAppearanceLightStatusBars = true
    }

    val boughtTickets by mainViewModel.boughtTickets.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AppToolbar(
            toolbarState = ToolbarState.Tickets(mainViewModel = mainViewModel),
            onBackClicked = { onBackClicked() },
            onCartClicked = { onCartClicked() }
        )
        LazyColumn {
            items(boughtTickets, key = { it.id }) { ticket ->
                TicketComp(
                    modifier = Modifier.fillMaxWidth(),
                    ticket = ticket,
                    buttonText = "View ticket",
                    buttonAction = { }
                )
                HorizontalDivider()
            }
        }
    }

}