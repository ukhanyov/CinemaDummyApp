import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemadummyapp.common.biometric.BiometricPromptManager
import com.example.cinemadummyapp.common.navigateSingleTopTo
import com.example.cinemadummyapp.interaction.InteractionScreen
import com.example.cinemadummyapp.onboarding.*

@Composable
fun CinemaNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Usage.route,
    navController: NavHostController,
    promptManager: BiometricPromptManager
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Onboarding.route) {
            OnboardingScreen(
                promptManager = promptManager,
                goToCreateAccount = {
                    navController.navigateSingleTopTo(CreateAccount.route)
                },
                goToLogin = {
                    navController.navigateSingleTopTo(Login.route)
                },
                goToUsage = {
                    navController.navigateSingleTopTo(Usage.route)
                },
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
            ConfirmCreateAccountScreen(
                goToAppUsage = {
                    navController.navigateSingleTopTo(Usage.route)
                }
            )
        }
        composable(route = Login.route) {
            LoginScreen(
                goToAppUsage = {
                    navController.navigateSingleTopTo(Usage.route)
                }
            )
        }
        composable(route = Usage.route) {
            InteractionScreen()
        }
    }
}