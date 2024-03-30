package com.example.cinemadummyapp.interaction.ticket_details

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.R
import kotlinx.coroutines.delay

@Composable
fun TicketDetailsScreen(
    mainViewModel: MainViewModel,
    ticketId: String,
    onBackClicked: () -> Unit = {},
    goToTickets: () -> Unit = {},
) {
    val localView = LocalView.current
    val activity = LocalView.current.context as Activity

    LaunchedEffect(key1 = true) {
        delay(150)
        activity.window.statusBarColor = Color.Black.toArgb()
        WindowCompat.getInsetsController(activity.window, localView)
            .isAppearanceLightStatusBars = false
    }

    val ticket by remember { mutableStateOf(mainViewModel.getTicket(ticketId)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .padding(16.dp)
                .align(Alignment.TopStart)
                .clickable { goToTickets() },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.size(40.dp))
            Text(
                text = "Once you buy a movie ticket simply scan the barcode to access to your movie.",
                color = Color.White,
                fontSize = 14.sp,
            )
            Column(
                modifier = Modifier
                    .sizeIn(minHeight = 400.dp)
                    .background(Color.White)
                    .clip(CircleShape)
            ) {
                Image(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = ticket.movie.image),
                    contentDescription = null
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = ticket.movie.title,
                        color = Color.Black,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Seat: ${ticket.seat}${ticket.row}",
                        color = Color.Black,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "Session: ${ticket.time}",
                        color = Color.Black,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.size(32.dp))
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_barrecode),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}