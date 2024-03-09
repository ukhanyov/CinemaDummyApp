package com.example.cinemadummyapp.interaction

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.TabHeight
import com.example.cinemadummyapp.common.navigateSingleTopTo
import com.example.cinemadummyapp.ui.theme.AppMainAccent
import com.example.cinemadummyapp.ui.theme.CinemaDummyAppTheme

@Preview
@PreviewScreenSizes
@PreviewDynamicColors
@PreviewFontScale
@PreviewLightDark
@Composable
fun InteractionScreenPreview() {
    CinemaDummyAppTheme {
        InteractionScreen()
    }
}

@Composable
fun InteractionScreen() {
    val activity = LocalView.current.context as Activity
    activity.window.statusBarColor = Color.Black.toArgb()
    WindowCompat.getInsetsController(
        activity.window,
        LocalView.current
    ).isAppearanceLightStatusBars = false

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppMainAccent)
    ) {
        val navController = rememberNavController()
//        val currentBackStack by navController.currentBackStackEntryAsState()
//        val currentDestination = currentBackStack?.destination
//        val currentScreen =
//            interactionScreens.find { it.route == currentDestination?.route } ?: Home

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    containerColor = Color(0xFF121212),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                        .offset(y = 50.dp),
                    onClick = { navController.navigateSingleTopTo(Tickets.route) }
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_ticket_logo),
                        contentDescription = null
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            bottomBar = {
                InteractionRow(
                    allScreens = interactionScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    modifier = Modifier
                        .selectableGroup()
                        .background(Color.Black)
                )
            }
        ) { innerPadding ->
            InteractionNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun InteractionRow(
    modifier: Modifier = Modifier,
    allScreens: List<InteractionDestination>,
    onTabSelected: (InteractionDestination) -> Unit,
) {
    Surface(
        modifier = Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            allScreens.forEach { screen ->
                InteractionTab(
                    text = screen.route,
                    icon = screen.icon,
                    onSelected = { onTabSelected(screen) },
                )
            }
        }
    }
}

@Composable
private fun InteractionTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
) {
    Icon(
        imageVector = icon,
        contentDescription = text,
        tint = Color.White,
        modifier = Modifier
            .fillMaxHeight()
            .size(48.dp)
            .clickable { onSelected() }
    )
}