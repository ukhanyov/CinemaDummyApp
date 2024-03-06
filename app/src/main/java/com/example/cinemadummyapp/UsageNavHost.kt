import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinemadummyapp.Home
import com.example.cinemadummyapp.Profile
import com.example.cinemadummyapp.Tickets

@Composable
fun UsageNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Home.route,
        modifier = modifier
    ) {
        composable(route = Home.route) {
        }
        composable(route = Tickets.route) {
        }
        composable(route = Profile.route) {
        }
    }
}