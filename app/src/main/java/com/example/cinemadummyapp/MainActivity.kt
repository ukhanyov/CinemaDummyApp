package com.example.cinemadummyapp

import CinemaNavHost
import Onboarding
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme
import tabRowScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CinemaApp()
        }
    }
}

@Composable
fun CinemaApp() {
    CinemaDummyAppTheme(dynamicColor = false) {
        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        val currentScreen =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Onboarding

        Scaffold(
            topBar = {
//                RallyTabRow(
//                    allScreens = rallyTabRowScreens,
//                    onTabSelected = { newScreen ->
//                        navController.navigateSingleTopTo(newScreen.route)
//                    },
//                    currentScreen = currentScreen
//                )
            }
        ) { innerPadding ->
            CinemaNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview
@Composable
fun CinemaAppPreview() {
    CinemaApp()
}