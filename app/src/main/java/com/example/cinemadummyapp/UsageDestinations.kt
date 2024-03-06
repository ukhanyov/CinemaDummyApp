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

//object SingleAccount : RallyDestination {
//    // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
//    // part of the RallyTabRow selection
//    override val icon = Icons.Filled.Money
//    override val route = "single_account"
//    const val accountTypeArg = "account_type"
//    val routeWithArgs = "$route/{$accountTypeArg}"
//    val arguments = listOf(
//        navArgument(accountTypeArg) { type = NavType.StringType }
//    )
//    val deepLinks = listOf(
//        navDeepLink { uriPattern = "rally://$route/{$accountTypeArg}" }
//    )
//}

// Screens to be displayed in the top RallyTabRow
val usageRowScreens = listOf(Home, Tickets, Profile)