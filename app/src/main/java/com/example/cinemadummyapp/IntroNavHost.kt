import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemadummyapp.ConfirmCreateAccount
import com.example.cinemadummyapp.CreateAccount
import com.example.cinemadummyapp.Login
import com.example.cinemadummyapp.Onboarding
import com.example.cinemadummyapp.screens.ConfirmCreateAccountScreen
import com.example.cinemadummyapp.screens.CreateAccountScreen
import com.example.cinemadummyapp.screens.LoginScreen
import com.example.cinemadummyapp.screens.OnboardingScreen

@Composable
fun CinemaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Onboarding.route,
        modifier = modifier
    ) {
        composable(route = Onboarding.route) {
            OnboardingScreen(
                goToCreateAccount = {
                    navController.navigateSingleTopTo(CreateAccount.route)
                },
                goToLogin = {
                    navController.navigateSingleTopTo(Login.route)
                }
            )
        }
        composable(route = CreateAccount.route) {
            CreateAccountScreen(
                onCreateAccountClicked = {
                    navController.navigateSingleTopTo(ConfirmCreateAccount.route)
                }
            )
        }
        composable(route = ConfirmCreateAccount.route) {
            ConfirmCreateAccountScreen()
        }
        composable(route = Login.route) {
            LoginScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }