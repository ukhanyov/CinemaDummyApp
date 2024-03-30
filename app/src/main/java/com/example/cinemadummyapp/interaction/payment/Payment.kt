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
    goToTickets: () -> Unit = {},
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
            mainViewModel = mainViewModel,
            onBackClicked = { onBackClicked() },
            onAddNewCardClicked = { state = AddNewCard },
            onConfirmClicked = { state = PaymentProgress },
        )

        AddNewCard -> {
            PaymentAddNewCard(
                mainViewModel = mainViewModel,
                returnToCardSelect = { state = SelectCards }
            )
        }

        PaymentProgress -> PaymentProgressComp(
            onSuccess = { state = PaymentSuccess },
            onFailure = { state = PaymentError },
        )

        PaymentSuccess -> PaymentSuccessComp(
            goToTickets = {
                mainViewModel.checkoutSuccess()
                goToTickets()
            },
        )

        PaymentError -> TODO()
    }

}