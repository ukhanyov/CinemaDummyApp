package com.example.cinemadummyapp.interaction.payment

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.common.toolbar.AppToolbar
import com.example.cinemadummyapp.common.toolbar.ToolbarState
import com.example.cinemadummyapp.ui.theme.AppMainAccent

@Composable
fun PaymentScreen(
    mainViewModel: MainViewModel,
    onBackClicked: () -> Unit = {},
) {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.White.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = true

    var state by remember { mutableStateOf(PaymentStates.SelectCards) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppToolbar(toolbarState = ToolbarState.Payment(), onBackClicked = { onBackClicked() })
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(48.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AppMainAccent),
            onClick = { },
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
                text = "Confirm",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}