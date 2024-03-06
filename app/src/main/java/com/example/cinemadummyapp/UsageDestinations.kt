package com.example.cinemadummyapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

interface UsageDestination {
    val icon: ImageVector
    val route: String
}

object Home : UsageDestination {
    override val icon = Icons.Filled.Home
    override val route = "usage/home"
}

object Tickets : UsageDestination {
    override val icon = Icons.Filled.DateRange
    override val route = "usage/tickets"
}

object Profile : UsageDestination {
    override val icon = Icons.Filled.Person
    override val route = "usage/profile"
}

val usageRowScreens = listOf(Home, Tickets, Profile)