package com.example.cinemadummyapp.interaction.payment

import android.app.Activity
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.cinemadummyapp.MainViewModel
import com.example.cinemadummyapp.interaction.payment.PaymentStates.*

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

    var state by remember { mutableStateOf(SelectCards) }

    when (state) {
        SelectCards -> PaymentSelectCardsComp(
            mainViewModel,
            onBackClicked = { onBackClicked() },
            onAddNewCardClicked = { state = AddNewCard }
        )

        AddNewCard -> TODO()
        PaymentProgress -> TODO()
        PaymentSuccess -> TODO()
        PaymentError -> TODO()
    }

}