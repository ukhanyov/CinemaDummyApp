package com.example.cinemadummyapp

import androidx.compose.ui.graphics.vector.ImageVector

interface IntroDestination {
    val icon: ImageVector?
    val route: String
}

object Onboarding : IntroDestination {
    override val icon = null
    override val route = "onboarding"
}

object CreateAccount : IntroDestination {
    override val icon = null
    override val route = "create_account"
}

object ConfirmCreateAccount : IntroDestination {
    override val icon = null
    override val route = "confirm_create_account"
}

object Login : IntroDestination {
    override val icon = null
    override val route = "login"
}