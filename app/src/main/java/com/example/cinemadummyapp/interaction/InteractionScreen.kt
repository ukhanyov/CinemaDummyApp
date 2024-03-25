package com.example.cinemadummyapp.interaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cinemadummyapp.R
import com.example.cinemadummyapp.common.TabHeight
import com.example.cinemadummyapp.common.navigateSingleTopTo
import com.example.cinemadummyapp.common.tickets.Ticket
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
        InteractionScreen(onProfileDeleted = {}, onProfileChange = {}, cart = emptyList())
    }
}

@Composable
fun InteractionScreen(
    onProfileDeleted: () -> Unit,
    onProfileChange: () -> Unit,
    goToCheckout: (List<Ticket>) -> Unit = {},
    cart: List<Ticket>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppMainAccent)
    ) {
        val navController = rememberNavController()

        var hideBottomNavigation by remember { mutableStateOf(false) }

        Scaffold(
            floatingActionButton = {
                if (hideBottomNavigation) return@Scaffold
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
                if (hideBottomNavigation) return@Scaffold
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
                modifier = Modifier.padding(innerPadding),
                hideBottomNavigation = { hideBottomNavigation = it },
                onProfileDeleted = { onProfileDeleted() },
                onProfileChange = { onProfileChange() },
                goToCheckout = { tickets -> goToCheckout(tickets) },
                cart = cart,
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