package com.example.cinemadummyapp

interface IntroDestination {
    val route: String
}

object Onboarding : IntroDestination {
    override val route = "onboarding"
}

object CreateAccount : IntroDestination {
    override val route = "create_account"
}

object ConfirmCreateAccount : IntroDestination {
    override val route = "confirm_create_account"
}

object Login : IntroDestination {
    override val route = "login"
}

object Usage : IntroDestination {
    override val route = "usage"
}