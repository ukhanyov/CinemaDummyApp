package com.example.cinemadummyapp.interaction.profile

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.common.tickets.Ticket
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState

//@Preview
//@PreviewScreenSizes
//@PreviewDynamicColors
//@PreviewFontScale
//@PreviewLightDark
//@Composable
//fun ProfileScreenPreview() {
//    CinemaDummyAppTheme {
//        ProfileScreen()
//    }
//}


@Composable
fun ProfileScreen(
    cart: List<Ticket>,
    onBackClicked: () -> Unit = {},
    onProfileDeleted: () -> Unit = {},
    onProfileChange: () -> Unit = {},
) {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.White.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = true

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 36.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(
            toolbarState = ToolbarState.Profile(cart = cart),
            onBackClicked = { onBackClicked() }
        )
        Column {
            Text(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 2.dp),
                text = "User email:",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 16.sp,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 2.dp),
                text = "username@email.com",
                color = Color.Black,
                fontSize = 16.sp,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 2.dp),
                text = "Password:",
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                fontSize = 16.sp,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 2.dp),
                text = "**********",
                color = Color.Black,
                fontSize = 16.sp,
            )
            Button(
                modifier = Modifier
                    .padding(horizontal = 48.dp, vertical = 16.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { onProfileChange() },
            ) {
                Text(
                    text = "Change",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp, vertical = 16.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = { onProfileDeleted() },
        ) {
            Text(
                text = "Delete Account",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }
    }
}