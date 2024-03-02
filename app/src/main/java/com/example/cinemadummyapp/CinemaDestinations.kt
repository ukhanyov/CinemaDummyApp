import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Contract for information needed on every navigation destination
 */
interface CinemaDestination {
    val icon: ImageVector?
    val route: String
}

/**
 * App navigation destinations
 */
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

//object Accounts : CinemaDestination {
//    override val icon = Icons.Filled.AttachMoney
//    override val route = "accounts"
//}
//
//object Bills : CinemaDestination {
//    override val icon = Icons.Filled.MoneyOff
//    override val route = "bills"
//}
//
//object SingleAccount : CinemaDestination {
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
//
//// Screens to be displayed in the top RallyTabRow
val tabRowScreens = listOf(Onboarding)
