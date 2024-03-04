package com.example.cinemadummyapp

import androidx.compose.ui.graphics.vector.ImageVector

interface CinemaDestination {
    val icon: ImageVector?
    val route: String
}

object Onboarding : CinemaDestination {
    override val icon = null
    override val route = "onboarding"
}

object CreateAccount : CinemaDestination {
    override val icon = null
    override val route = "create_account"
}

object ConfirmCreateAccount : CinemaDestination {
    override val icon = null
    override val route = "confirm_create_account"
}

object Login : CinemaDestination {
    override val icon = null
    override val route = "login"
}