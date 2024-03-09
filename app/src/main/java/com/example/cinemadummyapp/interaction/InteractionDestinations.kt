package com.example.cinemadummyapp.interaction

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

interface InteractionDestination {
    val icon: ImageVector
    val route: String
}

object Home : InteractionDestination {
    override val icon = Icons.Filled.Home
    override val route = "interaction/home"
}

object Tickets : InteractionDestination {
    override val icon = Icons.Filled.DateRange
    override val route = "interaction/tickets"
}

object Profile : InteractionDestination {
    override val icon = Icons.Filled.Person
    override val route = "interaction/profile"
}

val interactionScreens = listOf(Home, /*Tickets,*/ Profile)